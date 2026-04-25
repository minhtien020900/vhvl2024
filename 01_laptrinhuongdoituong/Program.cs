using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOP_CSDL_QLSV
{
    public class Department
    {
        public int DepartmentID { get; set; }
        public string DepartmentName { get; set; }
        public int FacultyID { get; set; }

        // mối quan hệ
        public Faculty Faculty { get; set; }
        public List<Student> Students { get; set; }
        public List<Course> Courses { get; set; }
    }

    public class Faculty
    {
        public int FacultyID { get; set; }
        public string FacultyName { get; set; }

        // mối quan hệ
        List<Department> Departments { get; set; }
    }
    public class Student
    {
        public int StudentID { get; set; }
        public string StudentName { get; set; }
        public int Age { get; set; }
        public int DepartmentID { get; set; }

        // mối quan hệ
        public Department Department { get; set; }
        public List<Enrollment> Enrollments { get; set; }
    }

    public class Course
    {
        public int CourseID { get; set; }
        public string CourseName { get; set; }
        public int DepartmentID { get; set; }
        public int Credit { get; set; }

        // mối quan hệ
        public Department Department { get; set; }
        public List<Enrollment> Enrollments { get; set; }
    }
    public class Enrollment
    {
        public int EnrollmentID { get; set; }
        public int StudentID { get; set; }
        public int CourseID { get; set; }
        public double Grade { get; set; }

        // mối quan hệ
        public Student Student { get; set; }
        public Course Course { get; set; }
    }

    public class QLSVDBContext
    {
        public List<Course> Courses { get; set; }
        public List<Department> Departments { get; set; }
        public List<Faculty> Facultys { get; set; }
        public List<Student> Students { get; set; }
        public List<Enrollment> Enrollments { get; set; }

        public QLSVDBContext()
        {
            SeederData();
        }

        public void SeederData()
        {
            Students = new List<Student>
            {
                new Student { StudentID = 1, StudentName = "Alice", Age = 20, DepartmentID = 1 },
                new Student { StudentID = 2, StudentName = "Bob", Age = 21, DepartmentID = 2 },
                new Student { StudentID = 3, StudentName = "Charlie", Age = 22, DepartmentID = 3 },
                new Student { StudentID = 4, StudentName = "David", Age = 23, DepartmentID = 4 }
            };

            Facultys = new List<Faculty>
           {
                new Faculty { FacultyID = 1, FacultyName = "Engineering" },
                new Faculty { FacultyID = 2, FacultyName = "Business" },
                new Faculty { FacultyID = 3, FacultyName = "Science" }
           };
            Courses = new List<Course>
            {
                new Course { CourseID = 1, CourseName = "C# Programming", DepartmentID = 1, Credit = 3 },
                new Course { CourseID = 2, CourseName = "Mathematics for Engineers", DepartmentID = 2, Credit = 3 },
                new Course { CourseID = 3, CourseName = "Physics for Scientists", DepartmentID = 3, Credit = 3 },
                new Course { CourseID = 4, CourseName = "Chemistry for Chemists", DepartmentID = 4, Credit = 3 }
            };

            Departments = new List<Department>
            {
                new Department { DepartmentID = 1, DepartmentName = "Computer Science", FacultyID = 1 },
                new Department { DepartmentID = 2, DepartmentName = "Mathematics", FacultyID = 1 },
                new Department { DepartmentID = 3, DepartmentName = "Physics", FacultyID = 3 },
                new Department { DepartmentID = 4, DepartmentName = "Chemistry", FacultyID = 3 }
            };


            Enrollments = new List<Enrollment>
            {
                new Enrollment { EnrollmentID = 1, StudentID = 1, CourseID = 1, Grade = 3.5 },
                new Enrollment { EnrollmentID = 2, StudentID = 1, CourseID = 2, Grade = 3.7 },
                new Enrollment { EnrollmentID = 3, StudentID = 2, CourseID = 2, Grade = 3.8 },
                new Enrollment { EnrollmentID = 4, StudentID = 3, CourseID = 3, Grade = 3.9 },
                new Enrollment { EnrollmentID = 5, StudentID = 4, CourseID = 4, Grade = 4.0 }
            };

            // nhập data cho các mối quan hệ
            foreach (var department in Departments)
            {
                department.Faculty = Facultys.FirstOrDefault(x => x.FacultyID == department.FacultyID);
                department.Students = Students.Where(x => x.DepartmentID == department.DepartmentID).ToList();
                department.Courses = Courses.Where(x => x.DepartmentID == department.DepartmentID).ToList();
            }

            // nhập data cho các mối quan hệ
            foreach (var course in Courses)
            {
                course.Department = Departments.FirstOrDefault(x => x.DepartmentID == course.DepartmentID);
            }

            // nhập data cho các mối quan hệ
            foreach (var student in Students)
            {
                student.Department = Departments.FirstOrDefault(x => x.DepartmentID == student.DepartmentID);
            }

            // nhập data cho các mối quan hệ
            foreach (var enrollment in Enrollments)
            {
                enrollment.Course = Courses.FirstOrDefault(x => x.CourseID == enrollment.CourseID);
                enrollment.Student = Students.FirstOrDefault(x => x.StudentID == enrollment.StudentID);
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Console.InputEncoding = Encoding.UTF8;
            Console.OutputEncoding = Encoding.UTF8;

            QLSVDBContext dbContext = new QLSVDBContext();

            // lấy danh sách khóa học của ngành Computer Science
            var lstCourse1 = dbContext.Courses.Where(x => x.Department.DepartmentName == "Computer Science").ToList();

            var lstCourse2 = from c in dbContext.Courses
                             join d in dbContext.Departments on c.DepartmentID equals d.DepartmentID
                             where d.DepartmentName == "Computer Science"
                             select c;

            foreach (var item in lstCourse1)
            {
                Console.WriteLine(item.CourseName);
            }
            Console.WriteLine("----------------------");
            foreach (var item in lstCourse2)
            {
                Console.WriteLine(item.CourseName);
            }
            Console.ReadKey();
        }
    }
}
