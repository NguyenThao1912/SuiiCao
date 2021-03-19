using RestSharp;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Web.Http;
using WebApplication2.Models;


namespace WebApplication2.Controllers
{
    public class StudentController : ApiController
    {        
        // GET: api/Student
        [Route("api/login/user={username}/pass={password}")]
        [HttpGet]
        public HttpResponseMessage GetCookiesLogin(string username,string password)
        {
            if (username == "" || password == "")
            {
                HttpError err = new HttpError("Bad Request");
                return Request.CreateResponse(HttpStatusCode.NotFound, err);
            }              
            User user = new User(username,password);
            bool res = PostAsync(user);
            return Request.CreateResponse(HttpStatusCode.OK, res);
        }
        
        public bool PostAsync(User user)
        {
            Uri _uri = new Uri(GlobalVar.URL);       
            CookieContainer container = new CookieContainer();
            HttpClientHandler handler = new HttpClientHandler();
            handler.CookieContainer = container;

            //Params in body request

            IEnumerable<KeyValuePair<string, string>> pairs = new List<KeyValuePair<string, string>>()
            {
                new KeyValuePair<string, string>("__EVENTVALIDATION",GlobalVar.EventValidation),
                new KeyValuePair<string, string>("__VIEWSTATE",GlobalVar.ViewState),
                new KeyValuePair<string, string>("__VIEWSTATEGENERATOR",GlobalVar.ViewStateGenerator),
                new KeyValuePair<string, string>("__EVENTARGUMENT",""),
                new KeyValuePair<string, string>("__EVENTTARGET",""),
                new KeyValuePair<string, string>("txtUserName",user.UserName),
                new KeyValuePair<string, string>("txtPassword",user.Password),
                new KeyValuePair<string, string>("btnSubmit","Đăng+nhập")
            };
            var val = new FormUrlEncodedContent(pairs);
            var httpRequestMessage = new HttpRequestMessage
            {
                Method = HttpMethod.Post,
                RequestUri = _uri,
                Headers =
                {
                    { HttpRequestHeader.CacheControl.ToString(),GlobalVar.Cache_Control },
                    { HttpRequestHeader.AcceptLanguage.ToString(), GlobalVar.Accept_Language },
                    { HttpRequestHeader.AcceptEncoding.ToString(), GlobalVar.Accept_Encoding },
                    { HttpRequestHeader.Connection.ToString(), GlobalVar.Connection },
                    { HttpRequestHeader.ContentType.ToString(), GlobalVar.ContentType },
                    { HttpRequestHeader.UserAgent.ToString(), GlobalVar.UserAgent },
                    { HttpRequestHeader.Accept.ToString(), GlobalVar.Accept }
                },
                Content = val
            };
            using (HttpClient client = new HttpClient(handler))
            {
                using (var response = client.SendAsync(httpRequestMessage).Result)
                {
                    using (HttpContent content = response.Content)
                    {
                         //GET COOKIES
                         var rescook = container.GetCookies(_uri).Cast<Cookie>().FirstOrDefault();
                        if (rescook != null)
                        {
                            GlobalVar.SignIn = rescook;
                        }

                        // ===============================================
                        // get HTML
                        //  string htmlresult = "";
                        // htmlresult = await content.ReadAsStringAsync();
                        if (rescook != null)
                            return true;
                        else
                            return false;
                    }
                }
            }             
        }
        [Route("api/timetable/semester")]
        public async Task<string> GetTimeTableAsync()
        {
            string html = await RequestTimetablePage();
            int start = html.LastIndexOf("drpSemester");
            //html = html.Remove(0, start+14);
            //  start = html.IndexOf("</select>");
             // html = html.Remove(start);
            if (html == "")
                return null;
            return html;
        }
        private async Task<string> RequestTimetablePage()
        {
            Uri _uri = new Uri(GlobalVar.Timetable_URL);
            CookieContainer container = new CookieContainer();
            HttpClientHandler handler = new HttpClientHandler();
            container.Add(_uri, GlobalVar.cookread);
            container.Add(_uri, GlobalVar.SignIn);
            handler.CookieContainer = container;
            var httpRequestMessage = new HttpRequestMessage
            {
                Method = HttpMethod.Get,
                RequestUri = _uri,
            };
            using (HttpClient client = new HttpClient(handler))
            {
                using (var response = client.SendAsync(httpRequestMessage).Result)
                {
                    using (HttpContent content = response.Content)
                    {
                        string htmlresult = "";
                        htmlresult = await content.ReadAsStringAsync();
                        File.WriteAllText(@"D:\xxx.txt", htmlresult);
                        if (htmlresult != null)
                            return htmlresult;
                        else
                            return "";
                    }
                }
            }
        }

    }
}
