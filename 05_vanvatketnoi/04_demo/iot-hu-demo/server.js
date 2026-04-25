const express = require("express");
const { EventHubConsumerClient } = require("@azure/event-hubs");
const WebSocket = require("ws");

const app = express();
const port = 3000;

app.use(express.static("public"));
app.listen(port, () => {
    console.log(`Web server running at http://localhost:${port}`);
});

const wss = new WebSocket.Server({ port: 8080 });
console.log("WebSocket server running at ws://localhost:8080");

const connectionString = "Endpoint=sb://australiacentraldednsd579622b.servicebus.windows.net/;SharedAccessKeyName=iothubowner;SharedAccessKey=I5d5+PiLu99foYCGuDVMmeq1WtMijTVmaAIoTJPl57Y=;EntityPath=iothub-ehub-demoiothub-55266209-302bcef0dd";
const consumerGroup = "$Default";

const client = new EventHubConsumerClient(consumerGroup, connectionString);


client.subscribe({
    processEvents: async (events) => {
        for (const event of events) {
            const msg = JSON.stringify(event.body);
            console.log("Received:", msg);


            wss.clients.forEach((ws) => {
                if (ws.readyState === WebSocket.OPEN) {
                    ws.send(msg);
                }
            });
        }
    },
    processError: async (err) => console.error(err)
});
