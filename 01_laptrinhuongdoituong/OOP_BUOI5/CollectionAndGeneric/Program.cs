using CollectionAndGeneric.Student;
using System;

namespace CollectionAndGeneric
{
    internal class Program
    {
        static void Main(string[] args)
        {

            Library library = new Library();
            for (int i = 0; i < 10; i++)
            {
                library.AddBook(new Book(i, $"Book {i}", $"Hai Dai {i}", 2018 + i));

            }
            library.displayAllBook();

            QuanLySinhVien qlSv = new QuanLySinhVien();

            qlSv.AddSinhVien(new SinhVien(1, "Minh Tien", 26, "Nam", 7));
            qlSv.displayAllSinhVien();

            Console.ReadKey();
        }
    }
}
