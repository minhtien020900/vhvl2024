using System;
using System.Collections.Generic;
using System.Linq;

namespace CollectionAndGeneric
{
    public class Library

    {
        List<Book> listBook = new List<Book>();
        public void AddBook(Book book)
        {

            var isExits = listBook.Any(x => x.Id == book.Id);
            if (!isExits)
            {
                listBook.Add(book);
            }

        }
        public void RemoveBook(int bookId)
        {
            // cach 1: xoas theo item
            var bookToRemove = listBook.FirstOrDefault(x => x.Id == bookId);
            var bookToRemove2 = listBook.First(x => x.Id == bookId);
            if (bookToRemove != null)
            {
                listBook.Remove(bookToRemove);
            }
            else
            {
                Console.WriteLine("");
            }

            // cach 2: Xoa theo index
            int index = listBook.FindIndex(x => x.Id == bookId);
            if (index > 0)
            {
                listBook.RemoveAt(index);
            }
            else
            {
                Console.WriteLine("");

            }

        }
        public void FindByTitle(string title)
        {
            var lstFound = listBook.Where(x => x.Title.Contains(title)).ToList();
            if (lstFound.Count > 0)
            {

                foreach (var item in lstFound)
                {
                    Console.WriteLine($"title nek: {item.Title}");
                }
            }
            else
            {
                Console.WriteLine("");
            }
        }

        public void CountBookByYear(int year)
        {
            int count = listBook.Count(x => x.PublishYear == year);
            Console.WriteLine($"So book trong nam {year} la: {count}");
        }

        public void displayAllBook()
        {

            //foreach (var item in book.get)
            //{
            foreach (var book in listBook)
            {

                Console.WriteLine($"Thong tin book {book.Id}");

                Console.WriteLine($"Title nek: {book.Title}");

                Console.WriteLine($"Tac gia nek: {book.Author}");

                Console.WriteLine($"Nam publish nek: {book.PublishYear}");
                Console.WriteLine("-----------------------");
            }

            //}
        }
    }

}
