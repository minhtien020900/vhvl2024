using System;
using System.Collections.Generic;
using System.Linq;

namespace GenericT
{
    public interface IRepository<T>
    {

        void Add(T entity);
        void Remove(T entity);
        void Update(T entity);

        List<T> GetAll();
        T getById(int id);
    }
    interface IUserRepository : IRepository<User>
    {
        bool CheckExists(int Id);
        User FindByName(string name);

    }
    class User
    {
        public int Id { get; set; }
        public string Name { get; set; }

    }
    class UserRepository : IUserRepository
    {
        List<User> users = new List<User>();
        public void Add(User entity)
        {
            if (CheckExists(entity.Id))
            {
                Console.WriteLine("Da ton tai");
            }
            else
            {
                throw new System.NotImplementedException();

            }
        }

        public bool CheckExists(int Id)
        {
            if (users.Any(x => x.Id == Id))
            {
                return true;

            }
            return false;
        }

        public User FindByName(string name)
        {
            return users.Find(x => x.Name.Contains(name));
        }

        public List<User> GetAll()
        {
            return users;
        }

        public User getById(int id)
        {
            return users.Find(x => x.Id == id);

        }

        public void Remove(User entity)
        {
            var index = users.FindIndex(x => x.Id == entity.Id);

            if (index == -1)
            {
                return;
            }
            else
            {
                users.RemoveAt(index);
            }
        }

        public void Update(User entity)
        {

            var user = users.Find(x => x.Id == entity.Id);

            if (user == null)
            {
                throw new System.NotImplementedException();

            }
            else
            {
                user = entity;
            }
        }
    }
    internal class Program
    {
        static void Main(string[] args)
        {
        }
    }
}
