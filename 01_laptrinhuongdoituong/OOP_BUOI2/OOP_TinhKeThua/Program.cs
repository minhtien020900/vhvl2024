using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOP_TinhKeThua
{
    public class Animal
    {
        public string Name
        {
            get;
            set;
        }
        public Animal(string name) {
            this.Name = name;
            
        }
        public void Eat()
        {
            Console.WriteLine("{0} is eating", Name);
        }
    }
    public class Dog : Animal
    {

        public string Color { get; set; }
        public Dog(string name, string color) : base(name) {
            this.Color = color;
        }
        public void Bark()
        {
            Console.WriteLine("{0} is barking", Name);
        }


    }
    class Program
    {
        static void Main(string[] args)
        {
            //Dog dog = new Dog("Cho Co","Mau Hong");
            //dog.Name = "Dog Pull";

            //dog.Bark();
            //dog.Eat();

            HInhHoc hinhCN = new HinhChuNhat("Hinh chu nhat nek");
            HInhHoc hinhTamGiac = new HinhTamGiac("Hinh tam giac",1,2,5);

            hinhTamGiac.TinhChuVi();

            Console.ReadKey();
        }
    }

    class HInhHoc {

        public string Name { get; set; }

        public HInhHoc(string name) {
            this.Name = name;
        
        }
        public virtual void TinhChuVi() { }
        public virtual void TinhDienTich() { }


    }

    class HinhTron : HInhHoc {
        public double Radius { get; set; }

      
        public HinhTron(string name)
            : base(name)
        {
            this.Name = name;
        }
        public override void TinhChuVi(){
            base.TinhChuVi();
        }

        public override void TinhDienTich()
        {
            base.TinhDienTich();
        }
    }

    class HinhChuNhat : HInhHoc
    {
        public double Radius { get; set; }

        public HinhChuNhat();
        public HinhChuNhat(string name):base(name) {
            this.Name = name;
        }
        public override void TinhChuVi()
        {
            base.TinhChuVi();
        }

        public override void TinhDienTich()
        {
            base.TinhDienTich();
        }
    }

    class HinhTamGiac : HInhHoc
    {
        public double Canh1 { get; set; }
        public double Canh2 { get; set; }
        public double Canh3 { get; set; }


        public HinhTamGiac(string name,double canh1,double canh2,double canh3):base(name) { 
            this.Canh1 = canh1;
            this.Canh2 = canh2;
            this.Canh3 = canh3;
        }
        public override void TinhChuVi()
        {
            base.TinhChuVi();
            Console.WriteLine("Tinh chu vi hinh tam giac: ");
        }

        public override void TinhDienTich()
        {
            base.TinhDienTich();
        }
    }
}
