using Happy_Habits_App.Forms;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class FoodController : ControllerBase
    {
        private readonly IFoodActivitiesService _foodActivitiesService;

        public FoodController(IFoodActivitiesService foodActivitiesService) => _foodActivitiesService = foodActivitiesService;

        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] FoodForm form)
        {
            Console.WriteLine("Trying to add food habit");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attributes");
            }

            Console.WriteLine("200");
            await _foodActivitiesService.CreateFoodActivity(form);
            return Ok("New food habit added");
        }

        [HttpGet("GetFoodActivities")]
        public async Task<IActionResult> RetrieveFoodActivities([FromQuery] string userId, [FromQuery] string date)
        {
            List<FoodDto> foodList = new List<FoodDto>();
            if (!string.IsNullOrEmpty(userId) && !string.IsNullOrEmpty(date)) 
            {
                foodList = await _foodActivitiesService.GetAllFoods(userId, date);
                return Ok(foodList);
            }

            return BadRequest(foodList);
        }

        [HttpDelete("DeleteHabit/{id}")]
        public async Task<IActionResult> DeleteHabit(string id)
        {
            await _foodActivitiesService.DeleteFoodActivity(id);
            return Ok("200");
        }
    }
}
