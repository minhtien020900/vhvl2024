using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BT_TongHop
{
    //interface iNhanVien
    //{
    //    void tinhLuong();
    //}
   abstract class NhanVien 
    {
        private int id { get; set; }

        public string name { get; set; }
        private int luongCoBan { get; set; }
        private int heSoLuong { get; set; }
        protected int luong
        {
            get
            {
                return this.luongCoBan * this.heSoLuong;
            }
            
        }
        NhanVien() { }
        public NhanVien(int id, string name, int heSoluong, int luongCb)
        {
            this.id = id;
            this.name = name;
            this.heSoLuong = heSoluong;
            this.luongCoBan = luongCb;
        }

        //public virtual void
        //    tinhLuong()
        //{
        //    Console.WriteLine(" --- DANG TINH LUONG LOADING... ---");

        //}
        public abstract void tinhLuong();

    }
    class NhanVienCT : NhanVien
    {

        public double tienTroCap { get; set; }
        public NhanVienCT( int id, string name, int heSoLuong, int luongCb, double tienTroCap) : base(id, name, heSoLuong, luongCb)
        {
            this.tienTroCap = tienTroCap;
        }
        public override void tinhLuong()
        {
            //base.tinhLuong();
            Console.WriteLine("Luong cua NV {0} la: ${1}", this.name, this.luong + tienTroCap);
        }
    }
    class NhanVienTV : NhanVien
    {
        public double tienHoaHong { get; set; }

       public NhanVienTV( int id, string name, int heSoLuong, int luongCb, double tienHoaHong) : base(id, name, heSoLuong, luongCb)
        {
            this.tienHoaHong = tienHoaHong;
        }
        public override void tinhLuong()
        {
            //base.tinhLuong();
            Console.WriteLine("Luong cua NV {0} la: ${1}", this.name, this.luong + tienHoaHong);

        }
    }
    internal class Program
    {
        static void Main(string[] args)
        {
            NhanVienCT nvct = new NhanVienCT(1,"Minh Tien",3,500000,150000);
            NhanVienTV nvtv = new NhanVienTV(2,"Hai Dai",5,200000,1500.2);

            nvct.tinhLuong();
            nvtv.tinhLuong();

            Console.ReadKey();
        }
    }
}
