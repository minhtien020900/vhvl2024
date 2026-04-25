namespace Buoi1
{
    internal class BT1
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Chuong trinh giai PT bac nhat dang ax + b = 0");
            Console.Write("Nhap so a: ");
            double a= Convert.ToDouble(Console.ReadLine());  
            
            Console.Write("Nhap so b: ");
            double b = Convert.ToDouble(Console.ReadLine());

         

            if (a == 0)
            {
                Console.WriteLine("PT vo nghiem");
            }

            else
            {
                double result = Convert.ToDouble(-b / a);
                Console.WriteLine("Nghiem cua PT la: {0}", result);
            }


            Console.ReadKey();
        }
    }
}
