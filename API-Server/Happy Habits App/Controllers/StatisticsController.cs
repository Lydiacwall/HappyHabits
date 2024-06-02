using Microsoft.AspNetCore.Mvc;
using DinkToPdf;
using DinkToPdf.Contracts;
using MailKit.Net.Smtp;
using MimeKit;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.IO;
using System.Threading.Tasks;
using Happy_Habits_App.Services;
using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class StatisticsController : ControllerBase
    {
        public IStatisticsService _statisticsService;

        public StatisticsController(IStatisticsService statisticsService)
        {
            _statisticsService = statisticsService;
        }

        [HttpPost("SendStatistics")]
        public async Task<IActionResult> SubmitStatistics([FromBody] StatisticsForm form)
        {
            try
            {
                Console.WriteLine("Trying to send statistics to doctor");
                await _statisticsService.GenerateEmail(form); 
                Console.WriteLine("200");
                return Ok("Form submitted and email sent successfully.");

            }
            catch (Exception ex)
            {
                Console.WriteLine("500 " + ex.Message);
                return StatusCode(500, $"Internal server error: {ex.Message}");
            }
        }
    }
}

/*{
  "email": "triantpanos30@gmail.com",
  "message": "Testing new feature"
}*/