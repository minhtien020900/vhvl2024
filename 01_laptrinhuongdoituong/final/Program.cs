using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace final
{

    public class NguoiDung
    {
        public int NguoidungId { get; set; }
        public string Ten { get; set; }
        public int Tuoi { get; set; }
        public string SDT { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }

        public List<LichSuThuChi> ListThuchi { get; set; }


    }

    public class LoaiThuChi
    {
        public int LoaiThuChiId { get; set; }
        public string Loai { get; set; }
        public List<DanhMuc> danhMucs { get; set; }

    }


    public class DanhMuc
    {
        public int DanhMucId { get; set; }
        public string TenDanhMuc { get; set; }
        public int LoaiThuChiId { get; set; }
        public LoaiThuChi loaiThuChi { get; set; }
        public List<LichSuThuChi> lichSuThuChis { get; set; }
    }

    public class LichSuThuChi
    {
        public int LSTCId { get; set; }
        public double SoTien { get; set; }
        public DateTime NgayThang { get; set; }
        public int NguoiDungId { get; set; }
        public int DanhMucID { get; set; }

        public NguoiDung NguoiDung { get; set; }
        public DanhMuc DanhMuc { get; set; }

    }

    public class QLTCDBContext
    {
        public List<NguoiDung> nguoiDungs { get; set; }
        public List<DanhMuc> DanhMucs { get; set; }

        public List<LoaiThuChi> LoaiThuChis { get; set; }

        public List<LichSuThuChi> LichSuThuChiLs { get; set; }

        public QLTCDBContext()
        {
            SeederData();
        }

        public void SeederData()
        {
            nguoiDungs = new List<NguoiDung>
            {
                new NguoiDung{NguoidungId = 1,Ten="Huynh Hai Dai",Tuoi = 20,SDT = "032541254",Email = "HaidaiTo@gmail.com", Password = "123" },
               new NguoiDung{NguoidungId = 2,Ten="Vo Minh Tien",Tuoi = 30,SDT = "032541254",Email = "TienBip@gmail.com", Password = "1234" },
                  new NguoiDung{NguoidungId = 3,Ten="Le Ninh Thuan",Tuoi = 22,SDT = "0325412547",Email = "Thuanle@gmail.com", Password = "12345" },

            };

            LoaiThuChis = new List<LoaiThuChi>
            {
                new LoaiThuChi{ LoaiThuChiId = 1, Loai = "Thu" },
                new LoaiThuChi{ LoaiThuChiId = 2, Loai = "Chi" }

            };

            DanhMucs = new List<DanhMuc>
            {
                new DanhMuc{ DanhMucId = 1, TenDanhMuc = "Ăn sáng", LoaiThuChiId = 2   },
                new DanhMuc{ DanhMucId = 2, TenDanhMuc = "Tiền Nhà", LoaiThuChiId = 2   },
                new DanhMuc{ DanhMucId = 3, TenDanhMuc = "Tiền đi chợ", LoaiThuChiId = 2   },
                new DanhMuc{ DanhMucId = 4, TenDanhMuc = "Vui chơi", LoaiThuChiId = 2   },
                new DanhMuc{ DanhMucId = 5, TenDanhMuc = "Điện Nước", LoaiThuChiId = 2   },
                new DanhMuc{ DanhMucId = 6, TenDanhMuc = "Tiền Lương", LoaiThuChiId = 1   },
                new DanhMuc{ DanhMucId = 7, TenDanhMuc = "Thưởng ", LoaiThuChiId = 1   },

            };

            LichSuThuChiLs = new List<LichSuThuChi>
            {
                
                #region Tiến 
                // ăn sáng id1
                new LichSuThuChi{ LSTCId = 1, SoTien = 35000,NgayThang = new DateTime(2024,11,01), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 2, SoTien = 15000,NgayThang = new DateTime(2024,11,02), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 3, SoTien = 0,NgayThang = new DateTime(2024,11,03), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 4, SoTien = 50000,NgayThang = new DateTime(2024,11,04), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 5, SoTien = 20000,NgayThang = new DateTime(2024,11,05), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 6, SoTien = 70000,NgayThang = new DateTime(2024,11,06), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 7, SoTien = 15000,NgayThang = new DateTime(2024,11,06), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 8, SoTien = 50000,NgayThang = new DateTime(2024,11,07), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 9, SoTien = 32000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 10, SoTien = 33000,NgayThang = new DateTime(2024,11,09), NguoiDungId = 2 , DanhMucID = 1  },
                                new LichSuThuChi{ LSTCId = 11, SoTien = 35000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 1  },

                                // tiền nhà 
                                new LichSuThuChi{ LSTCId = 12, SoTien = 3000000,NgayThang = new DateTime(2024,10,10), NguoiDungId = 2 , DanhMucID = 2  },
                                new LichSuThuChi{ LSTCId = 13, SoTien = 3000000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 2  },
                                // Tiền đi chợ 
                                new LichSuThuChi{ LSTCId = 14, SoTien = 120000,NgayThang = new DateTime(2024,11,01), NguoiDungId = 2 , DanhMucID = 3  },
                                new LichSuThuChi{ LSTCId = 15, SoTien = 150000,NgayThang = new DateTime(2024,11,03), NguoiDungId = 2 , DanhMucID = 3  },
                                new LichSuThuChi{ LSTCId = 16, SoTien = 85000,NgayThang = new DateTime(2024,11,05), NguoiDungId = 2 , DanhMucID = 3  },
                                new LichSuThuChi{ LSTCId = 17, SoTien = 70000,NgayThang = new DateTime(2024,11,07), NguoiDungId = 2 , DanhMucID = 3  },
                                new LichSuThuChi{ LSTCId = 18, SoTien = 250000,NgayThang = new DateTime(2024,11,09), NguoiDungId = 2 , DanhMucID = 3  },
                              
                                // tiền vui chơi 
                                new LichSuThuChi{ LSTCId = 19, SoTien = 100000,NgayThang = new DateTime(2024,11,06), NguoiDungId = 2 , DanhMucID = 4  },
                                new LichSuThuChi{ LSTCId = 20, SoTien = 30000,NgayThang = new DateTime(2024,11,07), NguoiDungId = 2 , DanhMucID = 4  },
                                new LichSuThuChi{ LSTCId = 21, SoTien = 850000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 2 , DanhMucID = 4  },
                                new LichSuThuChi{ LSTCId = 22, SoTien = 5000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 2 , DanhMucID = 4  },
                                new LichSuThuChi{ LSTCId = 23, SoTien = 300000,NgayThang = new DateTime(2024,11,09), NguoiDungId = 2 , DanhMucID = 4  },
                                new LichSuThuChi{ LSTCId = 24, SoTien = 350000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 4  },
                                //điện nước
                                new LichSuThuChi{ LSTCId = 25, SoTien = 500000,NgayThang = new DateTime(2024,10,10), NguoiDungId = 2 , DanhMucID = 5  },
                                new LichSuThuChi{ LSTCId = 26, SoTien = 520000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 5  },
                                // tiền lương
                                new LichSuThuChi{ LSTCId = 27, SoTien = 8500000,NgayThang = new DateTime(2024,10,10), NguoiDungId = 2 , DanhMucID = 6 },
                                new LichSuThuChi{ LSTCId = 28, SoTien = 8500000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 6 },
                                // tiền thưởng
                                new LichSuThuChi{ LSTCId = 29, SoTien = 500000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 2 , DanhMucID = 7 },
                                #endregion
                #region Đại 
                // ăn sáng
                new LichSuThuChi{ LSTCId = 30, SoTien = 15000,NgayThang = new DateTime(2024,12,01), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 31, SoTien = 20000,NgayThang = new DateTime(2024,12,05), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 32, SoTien = 25000,NgayThang = new DateTime(2024,11,11), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 33, SoTien = 15000,NgayThang = new DateTime(2024,11,20), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 34, SoTien = 15000,NgayThang = new DateTime(2024,12,22), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 35, SoTien = 20000,NgayThang = new DateTime(2024,12,23), NguoiDungId = 1 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 36, SoTien = 15000,NgayThang = new DateTime(2024,12,24), NguoiDungId = 1 , DanhMucID = 1  },
                // Tiền nhà
                new LichSuThuChi{ LSTCId = 37, SoTien = 2500000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 1 , DanhMucID = 2  },
                new LichSuThuChi{ LSTCId = 38, SoTien = 2500000,NgayThang = new DateTime(2024,12,15), NguoiDungId = 1 , DanhMucID = 2  },
                // đi chợ
                new LichSuThuChi{ LSTCId = 39, SoTien = 320000,NgayThang = new DateTime(2024,11,01), NguoiDungId = 1 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 40, SoTien = 275000,NgayThang = new DateTime(2024,11,07), NguoiDungId = 1 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 41, SoTien = 150000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 1 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 42, SoTien = 226000,NgayThang = new DateTime(2024,11,22), NguoiDungId = 1 , DanhMucID = 3  },
                // vui chơi
                new LichSuThuChi{ LSTCId = 43, SoTien = 90000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 1 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 44, SoTien = 120000,NgayThang = new DateTime(2024,11,16), NguoiDungId = 1 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 45, SoTien = 75000,NgayThang = new DateTime(2024,12,08), NguoiDungId = 1 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 46, SoTien = 360000,NgayThang = new DateTime(2024,11,19), NguoiDungId = 1 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 47, SoTien = 500000,NgayThang = new DateTime(2024,11,22), NguoiDungId = 1 , DanhMucID = 4  },
                //điện nước
                new LichSuThuChi{ LSTCId = 48, SoTien = 320000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 1 , DanhMucID = 5  },
                new LichSuThuChi{ LSTCId = 49, SoTien = 260000,NgayThang = new DateTime(2024,12,15), NguoiDungId = 1 , DanhMucID = 5  },
                //tiền lương
                new LichSuThuChi{ LSTCId = 50, SoTien = 8500000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 1 , DanhMucID = 6 },
                new LichSuThuChi{ LSTCId = 51, SoTien = 9600000,NgayThang = new DateTime(2024,12,10), NguoiDungId = 1 , DanhMucID = 6 },
                //tiền thưởng
                new LichSuThuChi{ LSTCId = 52, SoTien = 600000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 1 , DanhMucID = 7 },
                new LichSuThuChi{ LSTCId = 53, SoTien = 460000,NgayThang = new DateTime(2024,12,10), NguoiDungId = 1 , DanhMucID = 7 },
                #endregion
                #region Thuận 
                // ăn sáng
                new LichSuThuChi{ LSTCId = 54, SoTien = 15000,NgayThang = new DateTime(2024,12,01), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 55, SoTien = 20000,NgayThang = new DateTime(2024,12,05), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 56, SoTien = 25000,NgayThang = new DateTime(2024,12,11), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 57, SoTien = 15000,NgayThang = new DateTime(2024,12,20), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 58, SoTien = 15000,NgayThang = new DateTime(2024,12,22), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 59, SoTien = 20000,NgayThang = new DateTime(2024,12,23), NguoiDungId = 3 , DanhMucID = 1  },
                new LichSuThuChi{ LSTCId = 60, SoTien = 15000,NgayThang = new DateTime(2024,12,24), NguoiDungId = 3 , DanhMucID = 1  },
                // Tiền nhà
                new LichSuThuChi{ LSTCId = 61, SoTien = 2500000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 3 , DanhMucID = 2  },
                new LichSuThuChi{ LSTCId = 62, SoTien = 2500000,NgayThang = new DateTime(2024,12,15), NguoiDungId = 3 , DanhMucID = 2  },
                // đi chợ
                new LichSuThuChi{ LSTCId = 63, SoTien = 320000,NgayThang = new DateTime(2024,11,01), NguoiDungId = 3 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 64, SoTien = 275000,NgayThang = new DateTime(2024,11,07), NguoiDungId = 3 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 65, SoTien = 150000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 3 , DanhMucID = 3  },
                new LichSuThuChi{ LSTCId = 66, SoTien = 226000,NgayThang = new DateTime(2024,11,22), NguoiDungId = 3 , DanhMucID = 3  },
                // vui chơi
                new LichSuThuChi{ LSTCId = 67, SoTien = 90000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 3 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 68, SoTien = 120000,NgayThang = new DateTime(2024,11,16), NguoiDungId = 3 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 69, SoTien = 75000,NgayThang = new DateTime(2024,11,08), NguoiDungId = 3 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 70, SoTien = 360000,NgayThang = new DateTime(2024,11,19), NguoiDungId = 3 , DanhMucID = 4  },
                new LichSuThuChi{ LSTCId = 71, SoTien = 500000,NgayThang = new DateTime(2024,11,22), NguoiDungId = 3 , DanhMucID = 4  },
                //điện nước
                new LichSuThuChi{ LSTCId = 72, SoTien = 320000,NgayThang = new DateTime(2024,11,15), NguoiDungId = 3 , DanhMucID = 5  },
                new LichSuThuChi{ LSTCId = 73, SoTien = 260000,NgayThang = new DateTime(2024,12,15), NguoiDungId = 3 , DanhMucID = 5  },
                //tiền lương
                new LichSuThuChi{ LSTCId = 74, SoTien = 8500000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 3 , DanhMucID = 6 },
                new LichSuThuChi{ LSTCId = 75, SoTien = 9600000,NgayThang = new DateTime(2024,12,10), NguoiDungId = 3 , DanhMucID = 6 },
                //tiền thưởng
                new LichSuThuChi{ LSTCId = 76, SoTien = 600000,NgayThang = new DateTime(2024,11,10), NguoiDungId = 3 , DanhMucID = 7 },
                new LichSuThuChi{ LSTCId = 77, SoTien = 460000,NgayThang = new DateTime(2024,12,10), NguoiDungId = 3 , DanhMucID = 7 },
                #endregion
            };
            // nhập data cho các mối qua hệ
            foreach (var nguoidung in nguoiDungs)
            {
                nguoidung.ListThuchi = LichSuThuChiLs.Where(x => x.NguoiDungId == nguoidung.NguoidungId).ToList();
            }
            foreach (var loaiThuChi in LoaiThuChis)
            {
                loaiThuChi.danhMucs = DanhMucs.Where(x => x.LoaiThuChiId == loaiThuChi.LoaiThuChiId).ToList();
            }
            foreach (var danhMuc in DanhMucs)
            {
                danhMuc.loaiThuChi = LoaiThuChis.FirstOrDefault(x => x.LoaiThuChiId == danhMuc.LoaiThuChiId);

                danhMuc.lichSuThuChis = LichSuThuChiLs.Where(x => x.DanhMucID == danhMuc.DanhMucId).ToList();
            }
            foreach (var lichSuThuChi in LichSuThuChiLs)
            {
                lichSuThuChi.NguoiDung = nguoiDungs.FirstOrDefault(x => x.NguoidungId == lichSuThuChi.NguoiDungId);
                lichSuThuChi.DanhMuc = DanhMucs.FirstOrDefault(x => x.DanhMucId == lichSuThuChi.DanhMucID);
            }

        }
    }




    internal class Program
    {
        static QLTCDBContext _db = new QLTCDBContext();
        static int userID = -1;
        static void Main(string[] args)
        {
            Console.InputEncoding = Encoding.UTF8;
            Console.OutputEncoding = Encoding.UTF8;
            Console.BackgroundColor = ConsoleColor.Black;
            Console.ForegroundColor = ConsoleColor.White;
            Console.Clear();
            bool isRunning = true;
            bool isLogin = false;
            string tenNguoiDung = "";

            // Giao diện chọn người dùng
            while (!isLogin)
            {
                Console.Clear();
                Console.WriteLine("==============================================================================");
                Console.WriteLine("         Vui long chọn một người dùng theo danh sách phía dưới để đăng nhập");
                Console.WriteLine("==============================================================================");
                DanhSachNguoiDung();

                //Console.Write("Nhập Email:");
                //string email = Console.ReadLine();
                //Console.Write("Nhập Password:");
                //string pass = Console.ReadLine();


                // Nhận lựa chọn người dùng

                if (Login("TienBip@gmail.com", "1234") != null)
                {
                    isLogin = true;
                    userID = Login("TienBip@gmail.com", "1234").NguoidungId;
                    tenNguoiDung = Login("TienBip@gmail.com", "1234").Ten;
                }
                else
                {
                    isLogin = false;
                    Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại đăng nhập.");
                    Console.ReadKey();
                }

                if (isLogin)
                {
                    Console.Write("Đang đăng nhập");

                    // Hiệu ứng ba dấu chấm
                    for (int i = 0; i < 3; i++)
                    {
                        Console.Write(".");  // In ra dấu chấm
                        Thread.Sleep(10);  // Tạm dừng 500ms giữa mỗi dấu chấm
                    }
                }
            }
            // Lặp menu chính cho đến khi người dùng chọn thoát
            while (isRunning)
            {
                // Hiển thị menu
                Console.Clear();
                Console.WriteLine("=================================================");
                Console.WriteLine($"         CHÀO MỪNG {tenNguoiDung}");
                Console.WriteLine("=================================================");
                Console.WriteLine("\nVui lòng chọn một tùy chọn:");
                Console.WriteLine("1. Danh sách tổng tiền theo danh muc của người dùng");
                Console.WriteLine("2. Danh sách thu chi theo tháng của người dùng");
                Console.WriteLine("3. Danh sách Thu Chi theo danh mục của người dùng trong tháng");
                Console.WriteLine("4. Tổng tiền Thu, Chi và chênh lệch của người dùng trong tháng");
                Console.WriteLine("5. Danh sách chi của người dùng theo ngày tháng");
                Console.WriteLine("6. So sánh chi của 2 tháng");
                Console.WriteLine("7. Top 5 chi tiền nhiều nhất trong tháng");
                Console.WriteLine("8. Danh sách các danh mục thu/chi của người dùng");
                Console.WriteLine("9. Số tiền chi trung bình cho từng danh mục");
                Console.WriteLine("10.Thống kê tỷ lệ thu chi của các danh mục trong khoảng thời gian");

                Console.WriteLine("0. Thoát");

                // Nhận lựa chọn của người dùng
                string choice = Console.ReadLine();

                // Xử lý lựa chọn
                switch (choice)
                {
                    case "1":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        TongTienTheoDanhMucNguoiDung();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "2":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        DanhSachThuChiTheoThangCuaNguoiDung();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "3":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        DanhSachThuChiCuaNGuoiDungTheoThang();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "4":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        TongHopThuChiCuaNguoiDUngTheoThang();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "5":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        ChiCuaNguoiDungTheoNgayThang();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "6":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        sosanhchitheothang();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "7":
                        Console.Clear();
                        Console.WriteLine("Danh sách");
                        top5Chilonnhat();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;

                    case "8":
                        Console.Clear();
                        Console.WriteLine("Danh sách các danh mục của người dùng");
                        LayDanhSachCacDanhMucCuaNguoiDung();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;

                    case "9":
                        Console.Clear();
                        TrungBinhSoTienChiChoTungDanhMuc();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "10":
                        Console.Clear();
                        TyLeChiTieuCuaCacDanhMucTrongKhoangThoiGian();
                        Console.WriteLine("\nNhấn bất kỳ phím nào để quay lại menu.");
                        Console.ReadKey();
                        break;
                    case "0":
                        // Thoát chương trình
                        Console.WriteLine("\nCảm ơn bạn đã sử dụng chương trình!");
                        isRunning = false;
                        break;

                    default:
                        // Nếu lựa chọn không hợp lệ
                        Console.WriteLine("\nLựa chọn không hợp lệ! Nhấn bất kỳ phím nào để thử lại.");
                        Console.ReadKey();
                        break;
                }
            }

            Console.ReadKey();

        }
        #region Câu truy vấn linq
        static public NguoiDung Login(string email, string password)
        {
            try
            {
                var nguoidung = _db.nguoiDungs.FirstOrDefault(x => x.Email == email);
                if (nguoidung == null)
                {
                    Console.WriteLine("Email không tồn tại");
                    return null;
                }


                if (nguoidung.Password != password)
                {
                    Console.WriteLine("Mật khẩu không chính xác");
                    return null;
                }

                return nguoidung;

            }
            catch (Exception e)
            {

                throw;
            }
        }
        static public String TenNguoiDung(int id)
        {
            var nguoidung = _db.nguoiDungs.FirstOrDefault(x => x.NguoidungId == id).Ten;
            return nguoidung;
        }
        static public void DanhSachNguoiDung()
        {
            var lisNguoiDung = _db.nguoiDungs.ToList();
            foreach (var item in lisNguoiDung)
            {
                Console.WriteLine($"ID: {item.NguoidungId} - Họ Tên: {item.Ten} - Email: {item.Email} - Pass: {item.Password}");
            }

        }
        static public void TongTienTheoDanhMucNguoiDung()
        {
            // var listThuChi = _db.LichSuThuChiLs.Where(x => x.DanhMuc.TenDanhMuc == "Ăn sáng").ToList();
            var listThu = _db.LichSuThuChiLs.Join(_db.nguoiDungs,
                                                    x => x.NguoiDungId,
                                                    y => y.NguoidungId,
                                                    (x, y) => new
                                                    {
                                                        y.Ten,
                                                        y.NguoidungId,
                                                        x.SoTien,
                                                        x.NgayThang,
                                                        x.DanhMuc,
                                                    }).Join(_db.DanhMucs,
                                                    thuchi => thuchi.DanhMuc.DanhMucId,
                                                    danhmuc => danhmuc.DanhMucId,
                                                    (thuchi, danhmuc) => new
                                                    {
                                                        danhmuc.DanhMucId,
                                                        danhmuc.LoaiThuChiId,
                                                        danhmuc.TenDanhMuc,
                                                        danhmuc.lichSuThuChis,
                                                        thuchi.NgayThang,
                                                        thuchi.SoTien,
                                                        thuchi.Ten,
                                                        thuchi.NguoidungId,
                                                    }).Join(_db.LoaiThuChis,
                                                    loai => loai.LoaiThuChiId,
                                                    danhmuc2 => danhmuc2.LoaiThuChiId,
                                                    (loai, danhmuc2) => new
                                                    {
                                                        loai.NguoidungId,
                                                        loai.TenDanhMuc,
                                                        loai.Ten,
                                                        loai.SoTien,
                                                        loai.NgayThang,
                                                        danhmuc2.Loai,
                                                    }).GroupBy(g => new { g.NguoidungId, g.TenDanhMuc }).Select(
                z => new
                {
                    NguoiDungID = z.Key.NguoidungId,
                    TenDanhMuc = z.Key.TenDanhMuc,
                    Loai = z.First().Loai,
                    Ten = z.First().Ten,
                    TongSoTien = z.Sum(x => x.SoTien),

                }).Where(x => x.NguoiDungID == userID).ToList();
            Console.WriteLine("Danh sách Tổng tiền theo danh mục của người dùng");
            foreach (var item in listThu)
            {
                Console.WriteLine($"{item.Ten} - {item.TenDanhMuc} - {item.Loai} - {item.TongSoTien}");
            }
        }
        static public void DanhSachThuChiTheoThangCuaNguoiDung()
        {
            Console.Write("Nhập ngày bắt đầu (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date1 = Console.ReadLine();
            DateTime startDate = DateTime.Parse(date1);
            Console.Write("Nhập ngày kết thúc (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date2 = Console.ReadLine();
            DateTime endDate = DateTime.Parse(date2);
            var listThu = _db.LichSuThuChiLs.Where(x => x.NguoiDungId == userID && x.NgayThang >= startDate && x.NgayThang <= endDate)
                .OrderBy(x => x.NgayThang).ToList();
            foreach (var item in listThu)
            {
                Console.WriteLine($"{item.NguoiDung.Ten} - {item.NgayThang.ToString().Split(' ')[0]} - {item.DanhMuc.TenDanhMuc} - {item.SoTien}");
            }
        }
        static public void DanhSachThuChiCuaNGuoiDungTheoThang()
        {
            Console.Write("Nhập ngày bắt đầu (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date1 = Console.ReadLine();
            DateTime startDate = DateTime.Parse(date1);
            Console.Write("Nhập ngày kết thúc (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date2 = Console.ReadLine();
            DateTime endDate = DateTime.Parse(date2);
            var danhSachThuchiTheoDm = _db.LichSuThuChiLs
                .Where(ls => ls.NguoiDungId == userID && ls.NgayThang >= startDate && ls.NgayThang <= endDate).GroupBy(lse => new { lse.DanhMuc.TenDanhMuc })
                .Select(lsb => new
                {
                    ten = lsb.First().NguoiDung.Ten,
                    tongtien = lsb.Sum(x => x.SoTien),
                    Dm = lsb.Key.TenDanhMuc,
                    loai = lsb.First().DanhMuc.loaiThuChi.Loai,

                }).ToList();
            foreach (var item in danhSachThuchiTheoDm)
            {
                Console.WriteLine($"Người Dùng ID: {item.ten}, Số Tiền: {item.tongtien}, Danh mục: {item.Dm}, Loại: {item.loai}");
            }
        }
        static public void TongHopThuChiCuaNguoiDUngTheoThang()
        {
            Console.Write("Nhập ngày bắt đầu (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date1 = Console.ReadLine();
            DateTime startDate = DateTime.Parse(date1);
            Console.Write("Nhập ngày kết thúc (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date2 = Console.ReadLine();
            DateTime endDate = DateTime.Parse(date2);
            var danhSachThuChiTheoLoai = _db.LichSuThuChiLs
               .Where(ls => ls.NguoiDungId == userID && ls.NgayThang >= startDate && ls.NgayThang <= endDate).GroupBy(lse => new { lse.DanhMuc.loaiThuChi.Loai })
               .Select(lsb => new
               {
                   ten = lsb.First().NguoiDung.Ten,
                   tongtien = lsb.Sum(x => x.SoTien),
                   loai = lsb.Key.Loai,
               }).ToList();
            foreach (var item in danhSachThuChiTheoLoai)
            {
                Console.WriteLine($"Người Dùng ID: {item.ten}, Số Tiền: {item.tongtien}, Loại: {item.loai}");
            }
            var tienthu = danhSachThuChiTheoLoai.FirstOrDefault(x => x.loai == "Thu").tongtien;
            var tienchi = danhSachThuChiTheoLoai.FirstOrDefault(x => x.loai == "Chi").tongtien;
            Console.WriteLine($"Chêch lệch: {tienthu - tienchi}");
        }
        static public void ChiCuaNguoiDungTheoNgayThang()
        {
            Console.Write("Nhập ngày bắt đầu (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date1 = Console.ReadLine();
            DateTime startDate = DateTime.Parse(date1);
            Console.Write("Nhập ngày kết thúc (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date2 = Console.ReadLine();
            DateTime endDate = DateTime.Parse(date2);
            var thuChiTheoNgay = _db.LichSuThuChiLs.Where(x => x.NguoiDungId == userID && x.NgayThang >= startDate && x.NgayThang <= endDate && x.DanhMuc.loaiThuChi.Loai == "Chi")
                .Select(x => new
                {
                    TenNguoiDung = x.NguoiDung.Ten,
                    TenDanhMuc = x.DanhMuc.TenDanhMuc,
                    Loai = x.DanhMuc.loaiThuChi.Loai,
                    SoTien = x.SoTien,
                    NgayThang = x.NgayThang,

                }).ToList();

            var tongtien = thuChiTheoNgay.Sum(x => x.SoTien);
            foreach (var item in thuChiTheoNgay)
            {
                Console.WriteLine($"Tên: {item.TenNguoiDung} - Loại: {item.Loai} - Tên Danh Mục: {item.TenDanhMuc} - Ngày tháng: {item.NgayThang.ToString().Split(' ')[0]} - Số tiền: {item.SoTien}");
            }
            Console.WriteLine($"Tổng tiền tiêu: {tongtien}");

        }
        static void sosanhchitheothang()
        {
            Console.WriteLine("Nhập tháng thứ nhất (số nguyên 1-12): ");
            int thang1 = int.Parse(Console.ReadLine());
            Console.WriteLine("Nhập năm thứ nhất: ");
            int nam1 = int.Parse(Console.ReadLine());

            Console.WriteLine("Nhập tháng thứ hai (số nguyên 1-12): ");
            int thang2 = int.Parse(Console.ReadLine());
            Console.WriteLine("Nhập năm thứ hai: ");
            int nam2 = int.Parse(Console.ReadLine());

            // Tính tổng  chi cho tháng thứ nhất
            double tongChiThang1 = _db.LichSuThuChiLs
                .Where(ls => ls.NguoiDungId == userID && (ls.NgayThang.Month == thang1 && ls.NgayThang.Year == nam1) && ls.DanhMuc.loaiThuChi.Loai == "Chi")
                .Sum(ls => ls.SoTien);

            // Tính tổng thu chi cho tháng thứ hai
            double tongChiThang2 = _db.LichSuThuChiLs
                .Where(ls => ls.NguoiDungId == userID && (ls.NgayThang.Month == thang2 && ls.NgayThang.Year == nam2) && ls.DanhMuc.loaiThuChi.Loai == "Chi")
                .Sum(ls => ls.SoTien);

            // Hiển thị kết quả
            Console.WriteLine($"Tổng chi tháng {thang1}/{nam1}: {tongChiThang1} VND");
            Console.WriteLine($"Tổng chi tháng {thang2}/{nam2}: {tongChiThang2} VND");

            // So sánh và kết luận
            if (tongChiThang1 > tongChiThang2)
            {
                var chechlech = tongChiThang1 - tongChiThang2;
                Console.WriteLine($"Tháng {thang1}/{nam1} có chi tiêu nhiều hơn tháng {thang2}/{nam2} là {chechlech} VND.");
            }
            else if (tongChiThang1 < tongChiThang2)
            {
                var chechlech = tongChiThang2 - tongChiThang1;
                Console.WriteLine($"Tháng {thang1}/{nam1} có chi tiêu ít hơn tháng {thang2}/{nam2}  là {chechlech}.");
            }
            else
            {
                Console.WriteLine($"Chi tiêu của tháng {thang1}/{nam1} bằng với tháng {thang2}/{nam2}.");
            }
        }
        static void top5Chilonnhat()
        {
            Console.WriteLine("Nhập tháng cần xem (1-12):");
            int thang = int.Parse(Console.ReadLine());
            Console.WriteLine("Nhập năm cần xem:");
            int nam = int.Parse(Console.ReadLine());

            //  Lấy Top 5 khoản chi lớn nhất
            var topChiTieu = _db.LichSuThuChiLs
                .Where(ls => ls.NgayThang.Month == thang && ls.NgayThang.Year == nam && ls.NguoiDungId == userID && ls.DanhMuc.loaiThuChi.Loai == "Chi")
                .OrderByDescending(ls => ls.SoTien) // Số âm lớn hơn -> chi nhiều hơn
                .Take(5)
                .Select(ls => new
                {
                    ls.NgayThang,
                    SoTien = ls.SoTien,
                    ls.DanhMuc.TenDanhMuc
                })
                .ToList();

            // In kết quả
            if (topChiTieu.Any())
            {
                Console.WriteLine($"Top 5 khoản chi lớn nhất trong tháng {thang}/{nam}:");
                foreach (var chi in topChiTieu)
                {
                    Console.WriteLine($"{chi.NgayThang:dd/MM/yyyy}: {chi.SoTien:N0} VND - {chi.TenDanhMuc}");
                }
            }
            else
            {
                Console.WriteLine($"Không có khoản chi nào trong tháng {thang}/{nam}.");
            }
        }
        static public void LayDanhSachCacDanhMucCuaNguoiDung()
        {
            var dsDanhMuc = _db.nguoiDungs.Join(_db.LichSuThuChiLs,
                ngDung => ngDung.NguoidungId,
                dsThuChi => dsThuChi.NguoiDungId,
                (ngDung, dsThuChi) => dsThuChi).Join(_db.DanhMucs,
                   dsThuChiCuaNgDung => dsThuChiCuaNgDung.DanhMucID,
                   danhMuc => danhMuc.DanhMucId,
                    (dsThuChiCuaNgDung, danhMuc) => new { dsThuChiCuaNgDung, danhMuc })
                .Join(_db.LoaiThuChis,
                    ds => ds.danhMuc.LoaiThuChiId,
                    loaiThuChi => loaiThuChi.LoaiThuChiId,
                    (ds, loaiThuChi) => new
                    {
                        ds.danhMuc,
                        ds.dsThuChiCuaNgDung,
                        loaiThuChi
                    })
                .Where(dulieuTongHop => dulieuTongHop.dsThuChiCuaNgDung.NguoiDungId == userID).GroupBy(p => p.loaiThuChi.Loai)
                .Select(group =>
               new
               {
                   key = group.Key,
                   tenDanhMuc = string.Join(" - ", group.Select(x => x.danhMuc.TenDanhMuc).Distinct())
               });

            Console.WriteLine();

            foreach (var item in dsDanhMuc)
            {
                Console.WriteLine($"Các danh mục của {item.key}: {item.tenDanhMuc}");

            }
        }

        static public void TrungBinhSoTienChiChoTungDanhMuc()
        {
            var dsDanhMuc = _db.nguoiDungs.Join(_db.LichSuThuChiLs,
                ngDung => ngDung.NguoidungId,
                dsThuChi => dsThuChi.NguoiDungId,
                (ngDung, dsThuChi) => dsThuChi).Join(_db.DanhMucs,
                   dsThuChiCuaNgDung => dsThuChiCuaNgDung.DanhMucID,
                   danhMuc => danhMuc.DanhMucId,
                    (dsThuChiCuaNgDung, danhMuc) => new { dsThuChiCuaNgDung, danhMuc })
                .Join(_db.LoaiThuChis,
                    ds => ds.danhMuc.LoaiThuChiId,
                    loaiThuChi => loaiThuChi.LoaiThuChiId,
                    (ds, loaiThuChi) => new
                    {
                        ds.danhMuc,
                        ds.dsThuChiCuaNgDung,
                        loaiThuChi
                    })
                .Where(dulieuTongHop => dulieuTongHop.dsThuChiCuaNgDung.NguoiDungId == userID && dulieuTongHop.loaiThuChi.Loai == "Chi")

                .GroupBy(p => p.danhMuc.TenDanhMuc)
                .Select(group =>
               new
               {
                   key = group.Key,
                   //tenDanhMuc = string.Join(" - ", group.Select(x => x.danhMuc.TenDanhMuc).Distinct()),
                   tienTrungBinh = group.Average(x => x.dsThuChiCuaNgDung.SoTien)
               });

            Console.WriteLine("Số tiền chi trung bình của từng danh mục:");

            foreach (var item in dsDanhMuc)
            {
                Console.WriteLine($"{item.key}: {item.tienTrungBinh:N0} VNĐ");

            }
        }
        static public void TyLeChiTieuCuaCacDanhMucTrongKhoangThoiGian()
        {
            Console.WriteLine();

            Console.Write("Nhập ngày bắt đầu (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date1 = Console.ReadLine();
            DateTime startDate = DateTime.Parse(date1);
            Console.Write("Nhập ngày kết thúc (theo định dạng MM/dd/yyyy ví dụ: 12/25/2024):");
            string date2 = Console.ReadLine();
            DateTime endDate = DateTime.Parse(date2);


            var duLieu = _db.nguoiDungs.Join(_db.LichSuThuChiLs,
                ngDung => ngDung.NguoidungId,
                dsThuChi => dsThuChi.NguoiDungId,
                (ngDung, dsThuChi) => dsThuChi).Join(_db.DanhMucs,
                   dsThuChiCuaNgDung => dsThuChiCuaNgDung.DanhMucID,
                   danhMuc => danhMuc.DanhMucId,
                    (dsThuChiCuaNgDung, danhMuc) => new { dsThuChiCuaNgDung, danhMuc })
                .Join(_db.LoaiThuChis,
                    ds => ds.danhMuc.LoaiThuChiId,
                    loaiThuChi => loaiThuChi.LoaiThuChiId,
                    (ds, loaiThuChi) => new
                    {
                        ds.danhMuc,
                        ds.dsThuChiCuaNgDung,
                        loaiThuChi
                    })
                .Where(dulieuTongHop => dulieuTongHop.dsThuChiCuaNgDung.NguoiDungId == userID && dulieuTongHop.dsThuChiCuaNgDung.NgayThang >= startDate && dulieuTongHop.dsThuChiCuaNgDung.NgayThang <= endDate)

                .GroupBy(p => p.loaiThuChi.Loai)
                .Select(group =>
               new
               {
                   key = group.Key,
                   tongTien = group.Sum(x => x.dsThuChiCuaNgDung.SoTien),
                   tienTrungBinh = group.GroupBy(item => item.danhMuc.TenDanhMuc).Select(subGroup => new
                   {
                       TenDanhMuc = subGroup.Key,
                       tongTienDanhMuc = subGroup.Sum(x => x.dsThuChiCuaNgDung.SoTien),
                       TyLe = subGroup.Sum(x => x.dsThuChiCuaNgDung.SoTien) / group.Sum(x => x.dsThuChiCuaNgDung.SoTien) * 100
                   })
               });



            foreach (var item in duLieu)
            {
                Console.WriteLine();
                Console.WriteLine($"- Tổng tiền {item.key}: {item.tongTien:N0} VNĐ");
                foreach (var subItem in item.tienTrungBinh)
                {
                    Console.WriteLine($"# {subItem.TenDanhMuc}: {subItem.tongTienDanhMuc:N0} VNĐ => Chiếm {subItem.TyLe}%");
                }

            }
        }
        #endregion

    }
}