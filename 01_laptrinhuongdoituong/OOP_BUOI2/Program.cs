using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOP_BUOI2_24810045
{
    class SinhVien
    {
        public string hoVaTen;
        public int tuoi;
        public string mssv;
        public string email;
        public string sdt;
        public SinhVien()
        {
        }
        public SinhVien(string hoVaTen)
        {
            this.hoVaTen = hoVaTen;
        }
        public SinhVien(string hoVaTen, int tuoi)
        {
            this.hoVaTen = hoVaTen;
            this.tuoi = tuoi;
        }
        public void InThongTin()
        {
            Console.WriteLine("Ho ten: " + hoVaTen);
            Console.WriteLine("Tuoi: " + tuoi);
            Console.WriteLine("Ma so sinh vien: " + mssv);
            Console.WriteLine("Email: " + email);
            Console.WriteLine("Sdt: " + sdt);
        }
        public void LamBaiTap()
        {
            Console.WriteLine("Lam bai tap...");
        }
        ~SinhVien()
        {
            Console.WriteLine("Đối tượng {Name} bị hủy.");
        }



    }
    class Program
    {

        static void Main(string[] args)
        {
            /// SinhVien sv = new SinhVien();
            // sv.hoVaTen = "Minh Tien";
            // sv.tuoi = 25;
            //  sv.mssv = "Minh Tien";
            //  sv.email = "Minh Tien";
            //  sv.sdt = "09832123123";

 
            SinhVien sv2 = new SinhVien("toi la tien", 25);
            Sach sach = new Sach("Mat biec",129,"Minh Tien",129.000,"Kim Dong");
            SanPham sp = new SanPham("May quat", 325.000, "30-10-2024");

            Console.WriteLine("========= In thong tin Sinh vien: =========");
   
            sv2.InThongTin();

            Console.WriteLine("\n========= In thong tin Sach: =========");
            sach.InThongTin();


            Console.WriteLine("\n========= In thong tin san pham: =========");
            sp.InThongTin();

            Console.ReadKey();

        }


    }
}