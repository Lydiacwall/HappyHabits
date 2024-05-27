using Happy_Habits_App.Forms;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/Workout/[controller]")]
    [ApiController]
    public class FastActivityController : ControllerBase
    {
        private readonly IFastActivitiesService _fastActivitiesService;

        public FastActivityController(IFastActivitiesService fastActivitiesService) => _fastActivitiesService = fastActivitiesService;

        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] FastActivityForm form)
        {
            Console.WriteLine("Trying to add fast activity");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attrbutes");
            }
            Console.WriteLine("200");
            await _fastActivitiesService.AddFastActivity(form);
            return Ok("Fast Activity added succesfully");
        }
        [HttpGet("GetStatistics")]
        public async Task<IActionResult> GetStatistics([FromQuery] string userId, [FromQuery] int month, [FromQuery] int year, [FromQuery] string type)
        {
            Console.WriteLine("Trying to get fast activities statistics");

            if (userId == null || month == 0 || year == 0 || type == null)
            {
                Console.WriteLine("400");
                return BadRequest(null);
            }
            Console.WriteLine("200");
            FastActivitiesStatistics statistics = await _fastActivitiesService.GetFastActivitiesStatisticsAsync(userId, month, year, type);
            return Ok(statistics);
        }
    }
}
