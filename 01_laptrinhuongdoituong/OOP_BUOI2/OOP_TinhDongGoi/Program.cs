using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOP_TinhDongGoi
{

    public class Person
    {
        public string Name { get; set; }
        private int age;
        protected string address;
        public string major;
        public int GetAge()
        {
            return age;
        }
        public string MSSV
        {

            get
            {
                return MSSV;
            }
            set
            {

                if (major == "CNTT")
                {

                    MSSV = "248CNTT";

                }
            }
        }
        public void SetAge(int newAge)
        {
            if (newAge > 0 && newAge < 120)
            {
                age = newAge;
            }
        }
    }
    class SinhVien
    {

        public string hoVaTen;
        public int tuoi;
        private float diemTrungBinh
        {
            get
            {
                if (diemTrungBinh == 0)
                {
                    return 0;
                }
                else { return 10; }
            }
            set
            {
                diemTrungBinh = value;
            }
        }
    }
    class BankAccount
    {
        // soDu;
        // soTaikhoan;
        // chuTaiKhoan
        private float soDu;
        public string soTaiKhoan;
        public string chuTaiKhoan;


        protected void chuyenTien(float tienNhanVao)
        {
            if (tienNhanVao > 0)
            {
                soDu += tienNhanVao;
            }
        }

        private float kiemTraSoDu() {
            return soDu;
        }


        // chuyenTien;
        // kiemTraSoDu;
        // ap dung tinh chat dong goi
    }
    class Program
    {
        static void Main(string[] args)
        {

            Person person = new Person();
            person.Name = "John";



            Console.WriteLine("Ho va ten: {0}", person.Name);
            Console.WriteLine("Tuoi: ", person.GetAge());
            Console.ReadKey();
        }
    }
}
