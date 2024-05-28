using Happy_Habits_App.Forms;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/Workout/[controller]")]
    [ApiController]
    public class WeightsController : ControllerBase
    {
        private readonly IWeightsActivitiesService _weightsActivitiesService;

        public WeightsController(IWeightsActivitiesService weightsActivitiesService) => _weightsActivitiesService = weightsActivitiesService;

        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] WeightsForm form)
        {
            Console.WriteLine("trying to add weights activity");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attributes");
            }

            Console.WriteLine("200");
            await _weightsActivitiesService.AddWeightsActivity(form);
            return Ok("Weights added succesfully");
        }
        [HttpGet("GetStatistics")]
        public async Task<IActionResult> GetStatistics([FromQuery] string userId, [FromQuery] int month, [FromQuery] int year)
        {
            Console.WriteLine("Trying to get weights statistics");

            if (userId == null || month == 0 || year == 0) 
            {
                Console.WriteLine("400");
                return BadRequest(null);
            }
            Console.WriteLine("200");
            WeightsStatistics statistics = await _weightsActivitiesService.CalculateWeightsStatistics(userId, month, year);
            return Ok(statistics);
        }
    }
}
