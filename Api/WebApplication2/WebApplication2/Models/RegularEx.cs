using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;

namespace WebApplication2.Models
{
    public class RegularEx
    {
        private const string PatternSemester = @"^(<option\svalue=.)(?<key>\w+).+(?<semester>\d+_\d+_\d+)(<.option>)$";
        private const string PatternRemove = @"\n|\t|/[/]/";// ""
        private const string PatternReplace = @"\r"; // " "
        public static List<Semester> Getsemester(string html)
        {
            List<Semester> sm = new List<Semester>();
            html = Regex.Replace(html, PatternRemove, "");
            html = Regex.Replace(html, PatternReplace, " ");

            Regex rx = new Regex(PatternSemester);
            MatchCollection matches = rx.Matches(html);
            foreach (Match match in matches)
            {
                GroupCollection groups = match.Groups;
                var t = new Semester(groups["key"].Value,groups["semester"].Value);
                sm.Add(t);
            }
            return sm;
        }
    }
}