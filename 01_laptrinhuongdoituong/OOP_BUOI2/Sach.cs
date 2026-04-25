using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOP_BUOI2_24810045
{
    class Sach

    {
      
            public string tenSach;
            public string tacGia;
            public int soTrang;
            public double giaTien;
            public string nhaXuatBan;
            public Sach()
            {
            }
            public Sach(string tenSach)
            {
                this.tenSach = tenSach;
            }
            public Sach(string tenSach, int soTrang, string tacGia,double giaTien,string nhaXuatBan )
            {
                this.tacGia = tacGia;
                this.soTrang = soTrang;
                this.tenSach = tenSach;
                this.giaTien = giaTien;
                this.nhaXuatBan = nhaXuatBan;
            }
            public void InThongTin()
            {
                Console.WriteLine("Ho ten: " + tenSach);
                Console.WriteLine("NXB: " + nhaXuatBan);
                Console.WriteLine("Tac gia: " + tacGia);
                Console.WriteLine("So trang: " + soTrang);
      
            }
          
            ~Sach()
            {
                Console.WriteLine("Đối tượng Sach bị hủy.");
            }




    }
}
