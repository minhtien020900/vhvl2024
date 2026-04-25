namespace OOP_LINQ
{
    public class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public string City { get; set; }
    }
    public class Student
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Age { get; set; }
        public string Major { get; set; }
        public double GPA { get; set; }

        public int StudentId { get; set; }
        public int DepartmentId { get; set; }
    }

    public class Department
    {
        public int DepartmentId { get; set; }
        public string DepartmentName { get; set; }
        public int FacultyId { get; set; }
    }

    public class Faculty
    {
        public int FacultyId { get; set; }
        public string FacultyName { get; set; }
    }

    public class Course
    {
        public int CourseId { get; set; }
        public string CourseName { get; set; }
        public int DepartmentId { get; set; }
        public int Credits { get; set; }
    }

    public class Enrollment
    {
        public int EnrollmentId { get; set; }
        public int StudentId { get; set; }
        public int CourseId { get; set; }
        public double Grade { get; set; }
    }
    internal class Program
    {

        static void Main(string[] args)
        {

            var mc = new Program();
            //mc.ProcessClassStudentExample();
            mc.ProcessExampleEhancement();
            Console.ReadKey();


        }
        public void ProcessClassPersonExample()
        {
            List<Person> listPeople = new List<Person>{

             new Person{Name="Alice",Age=30,City="New York"},
             new Person{Name="Alice",Age=30,City="New York"},
             new Person{Name="Alice",Age=30,City="Chicago"},
             new Person{Name="Alice",Age=30,City="Chicago"},
             new Person{Name="Alice",Age=30,City="New York"},
             new Person{Name="Alice",Age=30,City="Chicago"},

            };
            // Loc ra nhung nguoi hon 25 tuoi
            var listResult = listPeople.Where(x => x.Age > 25).ToList();

            // Loc ra nhung nguoi o NewYork
            var listLiveNY = listPeople.Where(x => x.City == "New York").ToList();

            // Sap xep ds nguoi theo tuoi tang dan
            var listAgeOrderBy = listPeople.OrderBy(x => x.Age).ToList();

            // Dem so luong nguoi song o "Chicago"
            var countListLiveChicago = listPeople.Where(x => x.City == "Chicago").Count();
            var countListLiveChicago2 = listPeople.Count(x => x.City == "Chicago");

            // Tim nguoi tre nhat
            var youngstPerson = listPeople.OrderBy(x => x.Age).FirstOrDefault();
            //Console.WriteLine($"nhung nguoi hon 25 tuoi: {listResult[0]}");
            Console.WriteLine($"Dem so luong nguoi song o \"Chicago\": {countListLiveChicago}");
            Console.WriteLine($"Dem so luong nguoi song o \"Chicago\" 2: {countListLiveChicago2}");
        }

        public void ProcessClassStudentExample()
        {
            List<Student> students = new List<Student>
                {
                    new Student { Id = 1, Name = "Alice", Age = 22, Major = "Computer Science", GPA = 3.8 },
                    new Student { Id = 2, Name = "Bob", Age = 21, Major = "Mathematics", GPA = 3.5 },
                    new Student { Id = 3, Name = "Charlie", Age = 23, Major = "Physics", GPA = 3.9 },
                    new Student { Id = 4, Name = "David", Age = 20, Major = "Computer Science", GPA = 3.7 },
                    new Student { Id = 5, Name = "Eva", Age = 22, Major = "Mathematics", GPA = 3.6 },
                    new Student { Id = 6, Name = "Frank", Age = 24, Major = "Physics", GPA = 3.4 },
                    new Student { Id = 7, Name = "Grace", Age = 21, Major = "Computer Science", GPA = 3.9 },
                    new Student { Id = 8, Name = "Henry", Age = 23, Major = "Mathematics", GPA = 3.7 },
                    new Student { Id = 9, Name = "Ivy", Age = 22, Major = "Physics", GPA = 3.8 },
                    new Student { Id = 10, Name = "Jack", Age = 21, Major = "Computer Science", GPA = 3.5 }
                };
            var getStudentComputerScience = students.Where(x => x.Major == "Computer Science").ToList();
            var GPA = students.Where(x => x.GPA > 3.7).OrderBy(x => x.Name).Select(x => x.Name).ToList();
            var avg = students.GroupBy(x => x.Major);
            var getStudentByAge = students.GroupBy(x => x.Age).ToList();

            var GPA2 = students.GroupBy(x => x.Major).Select(g => new
            {
                Major = g.Key,
                Avg = g.Average(x => x.GPA),

            }).ToList();
            Console.WriteLine("\n---------- Diem TB cua moi nganh hoc CACH TOI UU: --------");

            foreach (var student in GPA2)
            {
                Console.WriteLine($"Nganh {student.Major}: {student.Avg}");
            }
            Console.WriteLine("\n---------- Diem TB cua moi nganh hoc CACH TOI UU: --------");


            var getStudentHaveTopAvgByMajor = students.GroupBy(x => x.Major).Select(g => new
            {
                Major = g.Key,
                TopAvg = g.OrderByDescending(x => x.GPA).FirstOrDefault(),

            });
            var getStudentByAge2 = students.GroupBy(x => x.Age).Select(g => new
            {
                Age = g.Key,
                Count = g.Count(),
            }).ToList();

            //foreach (var item in GPA2)
            //{
            //    Console.WriteLine($"Nganh {item.Major}: {item.Avg}");
            //    Console.WriteLine($"Diem cao nhat cua nganh {item.Major}: {item.MaxAvg}");
            //}
            Console.WriteLine("\nDiem TB cua moi nganh hoc:");

            //foreach (var avgItem in avg)
            //{
            //    //Console.WriteLine($"Nganh {avgItem.Key}: {avgItem.Average(x => x.GPA)}");
            //    Console.WriteLine($"Nganh {avgItem.Key}: {avgItem.Average(x => x.GPA)}");
            //    Console.WriteLine($"SV co diem cao nhat trong nganh {avgItem.Key}: {avgItem.Max(x => x.GPA)}");
            //}

            Console.WriteLine("So Sv trong moi do tuoi:");
            //foreach (var std in getStudentByAge)
            //{
            //    Console.WriteLine($"Tuoi {std.Key}: {std.Count()}");
            //}

            foreach (string gpa in GPA)
            {
                Console.WriteLine(gpa);
            }

        }
        public void ProcessExampleEhancement()
        {
            // Tạo dữ liệu mẫu
            List<Student> students = new List<Student>
{
    new Student { StudentId = 1, Name = "Alice", Age = 20, DepartmentId = 1 },
    new Student { StudentId = 2, Name = "Bob", Age = 21, DepartmentId = 1 },
    new Student { StudentId = 3, Name = "Charlie", Age = 22, DepartmentId = 2 },
    new Student { StudentId = 4, Name = "David", Age = 20, DepartmentId = 3 },
    new Student { StudentId = 5, Name = "Eva", Age = 23, DepartmentId = 2 }
};

            List<Department> departments = new List<Department>
{
    new Department { DepartmentId = 1, DepartmentName = "Computer Science", FacultyId = 1 },
    new Department { DepartmentId = 2, DepartmentName = "Mathematics", FacultyId = 1 },
    new Department { DepartmentId = 3, DepartmentName = "Physics", FacultyId = 2 },
    new Department { DepartmentId = 4, DepartmentName = "Chemistry", FacultyId = 2 }
};

            List<Faculty> faculties = new List<Faculty>
{
    new Faculty { FacultyId = 1, FacultyName = "Information Technology" },
    new Faculty { FacultyId = 2, FacultyName = "Natural Sciences" }
};

            List<Course> courses = new List<Course>
{
    new Course { CourseId = 1, CourseName = "Programming", DepartmentId = 1, Credits = 3 },
    new Course { CourseId = 2, CourseName = "Database", DepartmentId = 1, Credits = 4 },
    new Course { CourseId = 3, CourseName = "Calculus", DepartmentId = 2, Credits = 4 },
    new Course { CourseId = 4, CourseName = "Physics 101", DepartmentId = 3, Credits = 3 }
};

            List<Enrollment> enrollments = new List<Enrollment>
{
    new Enrollment { EnrollmentId = 1, StudentId = 1, CourseId = 1, Grade = 3.8 },
    new Enrollment { EnrollmentId = 2, StudentId = 1, CourseId = 2, Grade = 3.5 },
    new Enrollment { EnrollmentId = 3, StudentId = 2, CourseId = 1, Grade = 3.9 },
    new Enrollment { EnrollmentId = 4, StudentId = 3, CourseId = 3, Grade = 4.0 },
    new Enrollment { EnrollmentId = 5, StudentId = 4, CourseId = 4, Grade = 3.7 }
};



            //Lấy thông tin chi tiết về sinh viên, khoa và ngành học

            var listResult1 = from s in students
                              join d in departments on s.DepartmentId equals d.DepartmentId
                              join f in faculties on d.FacultyId equals f.FacultyId
                              select new
                              {
                                  StudentId = s.StudentId,
                                  StudentName = s.Name,
                                  DepartmentName = d.DepartmentName,
                                  FacultyName = f.FacultyName,
                              };



            // Lấy danh sách khóa học và điểm số của mỗi sinh viên
            var listResult2 = from s in students
                              join d in departments on s.DepartmentId equals d.DepartmentId
                              join c in courses on d.DepartmentId equals c.DepartmentId
                              select new
                              {
                                  StudentId = s.StudentId,
                                  StudentName = s.Name,
                                  StudentGPA = s.GPA,
                                  CourseName = c.CourseName,

                              };
            //Tính điểm trung bình của sinh viên theo khoa

            var listResult3 = (from s in students
                               join e in enrollments on s.StudentId equals e.StudentId
                               join d in departments on s.DepartmentId equals d.DepartmentId
                               join f in faculties on d.FacultyId equals f.FacultyId
                               select new
                               {
                                   StudentId = s.StudentId,
                                   StudentName = s.Name,
                                   StudenGPA = s.GPA,
                                   Grade = e.Grade,
                                   DepartmentName = d.DepartmentName,
                                   FacultyName = f.FacultyName,


                               }).ToList().GroupBy(x => x.FacultyName).Select(g =>
                             new
                             {
                                 FacultyName = g.Key,
                                 Avg = g.Average(x2 => x2.Grade)
                             });
            var listResult3_1 = from s in students
                                join e in enrollments on s.StudentId equals e.StudentId
                                join d in departments on s.DepartmentId equals d.DepartmentId
                                join f in faculties on d.FacultyId equals f.FacultyId
                                group e by f.FacultyName into g
                                select new { FacultyName = g.Key, AverageGPA = g.Average(t => t.Grade) };


            foreach (var faculty in listResult3)
            {
                Console.WriteLine($"FacultyName {faculty.FacultyName}: {faculty.Avg}");

            }

            foreach (var item in listResult3_1)
            {
                Console.WriteLine($"FacultyName {item.FacultyName}: {item.AverageGPA}");

            }
            //Tìm sinh viên có điểm cao nhất trong mỗi khóa học
            var listResult4 = from s in students
                              join e in enrollments on s.StudentId equals e.StudentId
                              join c in courses on e.CourseId equals c.CourseId
                              group new { Student = s, Grade = e.Grade } by c.CourseName into g
                              select new
                              {
                                  CoursesName = g.Key,
                                  TopStudent = g.OrderByDescending(t => t.Grade).FirstOrDefault(),
                              };
            //Thống kê số lượng sinh viên theo khoa và ngành

            var listResult5 = from s in students
                              join d in departments on s.DepartmentId equals d.DepartmentId
                              join f in faculties on d.FacultyId equals f.FacultyId
                              group s by new { FacultyName = f.FacultyName, DepartmentName = d.DepartmentName } into g
                              select new
                              {
                                  FacultyName = g.Key.FacultyName,
                                  DepartmentName = g.Key.DepartmentName,
                                  StudentCount = g.Count(),
                              }
                              ;
        }
    }
}
