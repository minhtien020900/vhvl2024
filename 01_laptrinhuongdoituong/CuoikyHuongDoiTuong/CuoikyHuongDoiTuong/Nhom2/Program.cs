using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Nhom2
{
    internal class Program
    {
        public class taiKhoan
        {
            public string taiKhoanID { get; set; }
            public string fullName { get; set; }
            public string matKhau { get; set; }
            public string email { get; set; }
            public string ngayTao { get; set; }

            //moi quan he
            public List<hoaDon> hoaDon { get; set; }
        }

        public class hoaDon
        {
            public string hoaDonID { get; set; }
            public string fullName { get; set; }
            public string ngayDat { get; set; }
            public string SDT { get; set; }
            public string diaChi { get; set; }
            //mối quan hệ
            public taiKhoan taiKhoan { get; set; }
            public hoaDonChiTiet hoaDonChiTiet { get; set; }
        }



        public class hoaDonChiTiet
        {
            public string chitietID { get; set; }

            public double gia { get; set; }

            public int soluong { get; set; }

            // mối quan hệ
            public hoaDon hoaDonID { get; set; }

            public List<sanPham> sanPham { get; set; }


        }

        public class sanPham
        {
            public string sanPhamID { get; set; }

            public double gia { get; set; }

            public string ngaytao { get; set; }


            public string mota { get; set; }

            public string tonkho { get; set; }

            public string loaihangID { get; set; }

            //mối quan hệ

            public hoaDon hoaDon { get; set; }

            public loaiHang loaiHang { get; set; }

        }

        public class loaiHang
        {
            public int loaiHangId { get; set; }
            public string tenLoai { get; set; }
            public string anhLoai { get; set; }
            public List<sanPham> sanPham { get; set; }
        }

        public class anhSanPham
        {
            public string anhID { get; set; }
            public string URL { get; set; }
            public sanPham sanPham { get; set; }
        }

        static void Main(string[] args)
        {
            Console.InputEncoding = Encoding.UTF8;
            Console.OutputEncoding = Encoding.UTF8;
            List<loaiHang> loaiHangs = new List<loaiHang>
            {
                new loaiHang { loaiHangId = 1, tenLoai = "Điện thoại", anhLoai = "dienthoai.jpg" },
                new loaiHang { loaiHangId = 2, tenLoai = "Laptop", anhLoai = "laptop.jpg" },
                new loaiHang { loaiHangId = 3, tenLoai = "Ipad", anhLoai = "ipad.jpg" },
                new loaiHang { loaiHangId = 4, tenLoai = "Tai nghe", anhLoai = "tainghe.jpg" },
                new loaiHang { loaiHangId = 5, tenLoai = "Đồng hồ", anhLoai = "dongho.jpg" }
            };

            // Danh sách sản phẩm
            List<sanPham> sanPhams = new List<sanPham>
            {
                new sanPham
                {
                    sanPhamID = "SP001",
                    ngaytao = "2024-11-01",
                    mota = "Điện thoại thông minh",
                    gia = 100,
                    tonkho = "100",
                    loaihangID = "1"
                },
                new sanPham
                {
                    sanPhamID = "SP002",
                    ngaytao = "2024-11-02",
                    mota = "Laptop gaming",
                    gia = 200,
                    tonkho = "50",
                    loaihangID = "2"
                },
                new sanPham
                {
                    sanPhamID = "SP003",
                    ngaytao = "2024-11-03",
                    mota = "Ipad",
                    gia = 300,
                    tonkho = "150",
                    loaihangID = "3"
                },
                new sanPham
                {
                    sanPhamID = "SP004",
                    ngaytao = "2024-11-04",
                    mota = "tai nghe",
                    gia = 400,
                    tonkho = "510",
                    loaihangID = "4"
                },
                new sanPham
                {
                    sanPhamID = "SP005",
                    ngaytao = "2024-11-05",
                    mota = "Đồng hồ",
                    gia = 500,
                    tonkho = "200",
                    loaihangID = "5"
                }
            };

            // Danh sách ảnh sản phẩm
            List<anhSanPham> anhSanPhams = new List<anhSanPham>
            {
                new anhSanPham { anhID = "IMG001", URL = "sp001_img1.jpg", sanPham = sanPhams[0]},
                new anhSanPham { anhID = "IMG002", URL = "sp001_img2.jpg", sanPham = sanPhams[0]},
                new anhSanPham { anhID = "IMG003", URL = "sp002_img1.jpg", sanPham = sanPhams[1]},
                new anhSanPham { anhID = "IMG004", URL = "sp002_img2.jpg", sanPham = sanPhams[1]},
                new anhSanPham { anhID = "IMG005", URL = "sp003_img1.jpg", sanPham = sanPhams[2]},
                new anhSanPham { anhID = "IMG006", URL = "sp003_img2.jpg", sanPham = sanPhams[2]},
                new anhSanPham { anhID = "IMG007", URL = "sp004_img1.jpg", sanPham = sanPhams[3]},
                new anhSanPham { anhID = "IMG008", URL = "sp004_img2.jpg", sanPham = sanPhams[3]},
                new anhSanPham { anhID = "IMG009", URL = "sp005_img1.jpg", sanPham = sanPhams[4]},
                new anhSanPham { anhID = "IMG010", URL = "sp005_img2.jpg", sanPham = sanPhams[4]},
            };

            // Danh sách chi tiết hóa đơn
            List<hoaDonChiTiet> hoaDonChiTiet = new List<hoaDonChiTiet>
            {
                new hoaDonChiTiet { chitietID = "HD001", gia = 300, soluong = 2, sanPham = new List<sanPham> {sanPhams[0], sanPhams[1]} },
                new hoaDonChiTiet { chitietID = "HD002", gia = 200, soluong = 1, sanPham = new List<sanPham> {sanPhams[1]} },
                new hoaDonChiTiet { chitietID = "HD003", gia = 600, soluong = 3, sanPham = new List<sanPham> {sanPhams[0], sanPhams[1], sanPhams[2]} }
            };

            // Danh sách hóa đơn
            List<hoaDon> hoaDons = new List<hoaDon>
            {
                new hoaDon
                {
                    hoaDonID = "HD001",
                    fullName = "Nguyễn Văn A",
                    ngayDat = "2024-11-20",
                    SDT = "0123456789",
                    diaChi = "Hà Nội",
                    hoaDonChiTiet = hoaDonChiTiet[0]
                },
                new hoaDon
                {
                    hoaDonID = "HD002",
                    fullName = "Trần Thị B",
                    ngayDat = "2024-11-21",
                    SDT = "0987654321",
                    diaChi = "TP. HCM",
                    hoaDonChiTiet = hoaDonChiTiet[1]
                },
                new hoaDon
                {
                    hoaDonID = "HD003",
                    fullName = "Nguyễn Văn A",
                    ngayDat = "2024-11-20",
                    SDT = "0123456789",
                    diaChi = "Hà Nội",
                    hoaDonChiTiet = hoaDonChiTiet[2]
                }
            };

            // Danh sách tài khoản
            List<taiKhoan> taiKhoans = new List<taiKhoan>
            {
                new taiKhoan
                {
                    taiKhoanID = "TK001",
                    fullName = "Nguyễn Văn A",
                    matKhau = "123456",
                    email = "nguyenvana@gmail.com",
                    ngayTao = "2024-11-01",
                },
                new taiKhoan
                {
                    taiKhoanID = "TK002",
                    fullName = "Trần Thị B",
                    matKhau = "password",
                    email = "tranthib@gmail.com",
                    ngayTao = "2024-11-02",
                    hoaDon = new List<hoaDon> { hoaDons[1]}
                }
            };
            foreach (var item in taiKhoans)
            {
                item.hoaDon = hoaDons.Where(x => x.tai)
            }

            var lstTaikhoan = from i in taiKhoans
                              select new
                              {
                                  ID = i.taiKhoanID,
                                  Name = i.fullName,
                                  TenSanPham = i.hoaDon.ToList(),
                              }
                              ;

            foreach (var item in lstTaikhoan)
            {
                Console.WriteLine($"{item.ID} - {item.Name} - {item.TenSanPham}");
            }

            var lstTaikhoan2 = from i in hoaDonChiTiet
                               join p in hoaDons on i.chitietID equals p.hoaDonID

                               select i;

            foreach (var item in lstTaikhoan2)
            {
                Console.WriteLine(item.chitietID);
            }
            Console.ReadKey();
        }
    }
}
