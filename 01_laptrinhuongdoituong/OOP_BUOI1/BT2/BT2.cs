namespace BT2
{
    internal class BT2
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Chuong trinh giai PT bac hai dang ax² + bx + c = 0");
      
            Console.Write("Nhap so a: ");
            double a = Convert.ToDouble(Console.ReadLine());

            Console.Write("Nhap so b: ");
            double b = Convert.ToDouble(Console.ReadLine());
            Console.Write("Nhap so c: ");
            double c = Convert.ToDouble(Console.ReadLine());

            double delta = b*b - 4*a*c;


            if (delta < 0)
            {
                Console.WriteLine("PT vo nghiem");
            }

            else if (delta == 0)
            {
                double result = -b / 2 * a;
                Console.WriteLine("PT có nghiem kép x1 = x2 = {0}", result);
            }
            else if (delta > 0)
            {
                double x1= (-b + Math.Sqrt(delta))/(2*a);
                double x2= (-b - Math.Sqrt(delta)) /(2*a);
                Console.WriteLine("PT có nghiem x1 = {0}", x1);
                Console.WriteLine("PT có nghiệm x2 = {0}", x2);

            }
            Console.ReadKey();
        }
    }
}
