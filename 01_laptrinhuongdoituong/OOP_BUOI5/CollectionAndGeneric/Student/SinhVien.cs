namespace CollectionAndGeneric.Student
{
    public class SinhVien

    {
        public int Id { get; set; }
        public string FullName { get; set; }
        public int Age { get; set; }
        public string Male { get; set; }
        public float PointAvg { get; set; }
        public SinhVien() { }
        public SinhVien(int id, string fullName, int age, string male, float pointAvg)
        {
            Id = id; FullName = fullName; Age = age; Male = male; PointAvg = pointAvg;
        }
    }
}
