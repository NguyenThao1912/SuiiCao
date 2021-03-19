using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.Models
{
    public class User
    {
        public string UserName { get; set; }
        public string Password { get; set; } = "0b29f80d5b2cee0ffcc1621fb4066c39";

        public User(string userName, string password)
        {
            UserName = userName;
            Password = password;
        }
    }
}