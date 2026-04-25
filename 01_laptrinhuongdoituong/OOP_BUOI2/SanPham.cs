using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOP_BUOI2_24810045
{
    class SanPham
    {
        public string ten;

        public double giaTien;
        public string ngayNhapKho;
        public SanPham()
        {
        }
        public SanPham(string ten)
        {
            this.ten = ten;
        }
        public SanPham(string ten, double giaTien, string ngayNhapKho)
        {
            this.ten = ten;
            this.giaTien = giaTien;
            this.ngayNhapKho = ngayNhapKho;


        }
        public void InThongTin()
        {
            Console.WriteLine("Ten SP: " + ten);
            Console.WriteLine("Gia tien: " + giaTien);
            Console.WriteLine("Ngay nhap kho " + ngayNhapKho);

        }

        ~SanPham()
        {
            Console.WriteLine("Đối tượng San pham bị hủy.");
        }

    }
}
