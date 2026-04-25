using System;
using System.Collections.Generic;
using System.Linq;

namespace CollectionAndGeneric.Student
{
    public class QuanLySinhVien
    {
        List<SinhVien> listSinhVien = new List<SinhVien>();
        public void AddSinhVien(SinhVien sinhVien)
        {
            var isExist = listSinhVien.Any(x => x.Id == sinhVien.Id);
            if (isExist)
            {

                Console.WriteLine($"Sinh vien {sinhVien.Id} da ton tai !!!");
            }
            else
            {
                listSinhVien.Add(sinhVien);
            }
        }
        public void findSinhVienByName(string name)
        {
            var lstFound = listSinhVien.Where(x => x.FullName.Contains(name)).ToList();
        }
        public void displayAllSinhVien()
        {
            foreach (var s in listSinhVien)
            {
                Console.WriteLine($"\n --- Thong tin sinh vien {s.Id} ---- ");

                Console.WriteLine($"Name nek: {s.FullName}");
                Console.WriteLine($"Male nek: {s.Male}");
                Console.WriteLine($"Age nek: {s.Age}");
                Console.WriteLine($"PointAvg nek: {s.PointAvg}");


            }
        }
        public void EditSinhVienInfo(int Id, int newAvgPoint)
        {
            SinhVien sinhVien = listSinhVien.Find(x => x.Id == Id);
            if (sinhVien == null)
            {
                Console.WriteLine($"Sinh vien {0} khong ton tai");
            }
            else
            {
                sinhVien.PointAvg = newAvgPoint;
            }

        }

        public void RemoveSinhVien(int Id)
        {
            int index = listSinhVien.FindIndex(x => x.Id == Id);
            if (index == -1)
            {
                Console.WriteLine($"Sinh vien {0} khong ton tai");
            }
            else
            {
                listSinhVien.RemoveAt(index);
                Console.WriteLine($"Remove sinh vien {0} thanh cong");
            }

        }

        public void sortByPoit(bool tangDan)
        {

            listSinhVien.OrderBy(x => x.PointAvg).ToList();
        }
    }
}
