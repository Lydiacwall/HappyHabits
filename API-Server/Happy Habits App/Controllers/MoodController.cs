using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MoodController : ControllerBase
    {
        private readonly IMoodActivitiesService _moodActivitiesService;

        public MoodController(IMoodActivitiesService moodActivitiesService) => _moodActivitiesService = moodActivitiesService;


        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] MoodForm form)
        {
            Console.WriteLine("Trying to add mood habit");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attributes"); // This sends plain text
            }
            Console.WriteLine("200");
            await _moodActivitiesService.AddMoodActivity(form);
            return new ContentResult { Content = "New mood habit added", ContentType = "text/plain", StatusCode = 200 };
        }
    }
}
