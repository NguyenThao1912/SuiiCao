using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication2.Models
{
    public class Semester
    {
        public string  SemesterKey { get; set; }
        public string SemesterName { get; set; }
        public Semester(string key,string name)
        {
            this.SemesterKey = key;
            this.SemesterName = name;
        }
    }
}