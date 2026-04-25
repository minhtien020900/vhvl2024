using System;
using System.Linq;
using System.Windows.Forms;

namespace OOP_ButtonClick
{
    public partial class Form1 : Form
    {
        public delegate void ButtonClickHander(object sender);
        public event ButtonClickHander buttonClickHander;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            buttonClickHander += displayMsgBox;
        }


        private void displayMsgBox(object sender)
        {
            MessageBox.Show($"{(sender as Button).Text} clicked");
        }
        private void OnButtonClicked(object sender)
        {
            buttonClickHander?.Invoke(sender);
        }
        private void btnRender_Click(object sender, EventArgs e)
        {
            Panel panel = this.Controls.Find("renderBtnArea", true).FirstOrDefault() as Panel;

            if (panel != null)
            {
                int quantity = int.Parse(textBox1.Text);
                Button[] buttons = createListButtonDynamic(quantity);

                panel.Controls.AddRange(buttons);

                panel.AutoScroll = true;

            }
        }

        private Button[] createListButtonDynamic(int quantity)
        {
            Button[] buttons = new Button[quantity];
            for (int i = 0; i < quantity; i++)
            {
                Button btn = new Button();
                btn.Text = $"Button {i + 1}";

                btn.Click += (s, args) => OnButtonClicked(s);

                buttons[i] = btn;
            }
            return buttons;
        }
    }
}
