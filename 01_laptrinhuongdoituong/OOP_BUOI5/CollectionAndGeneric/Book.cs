namespace CollectionAndGeneric
{
    public class Book

    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public int PublishYear { get; set; }
        public Book() { }
        public Book(int id, string title, string author, int publishYear)
        {
            Id = id;
            Title = title;
            Author = author;
            PublishYear = publishYear;
        }
    }

}
