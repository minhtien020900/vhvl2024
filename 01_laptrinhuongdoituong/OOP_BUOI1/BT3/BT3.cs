namespace BT3
{
    internal class BT3
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Chuong trinh in ra thoi gian tiep theo");

            Console.Write("Nhap gio: ");
            int hour = Convert.ToInt32(Console.ReadLine());

            Console.Write("Nhap phut: ");
            int minute = Convert.ToInt32(Console.ReadLine());
            Console.Write("Nhap giay: ");
            int seconds = Convert.ToInt32(Console.ReadLine());

            seconds += 1;
            if (seconds == 60)
            {
                seconds = 00;
                minute += 1;
                if (minute == 60)
                {
                    minute = 00;
                    hour += 1;
                    if (hour == 24)
                    {
                            hour = 00;
                    }
                }
            }

            Console.WriteLine("Ket qua: {0}h:{1}m:{2}s",hour,minute,seconds);
        }
            
    }
}
