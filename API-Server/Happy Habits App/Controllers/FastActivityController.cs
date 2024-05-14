using Happy_Habits_App.Forms;
using Happy_Habits_App.Services;
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
    }
}
