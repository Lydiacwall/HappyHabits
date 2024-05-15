using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ToiletController : ControllerBase
    {
        private readonly IToiletActivitiesService _toiletActivitiesService;

        public ToiletController(IToiletActivitiesService toiletActivitiesService) => _toiletActivitiesService = toiletActivitiesService;


        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] ToiletForm form)
        {
            Console.WriteLine("Trying to add toilet habit");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attributes"); // This sends plain text
            }
            Console.WriteLine("200");
            await _toiletActivitiesService.AddToiletActivity(form);
            return new ContentResult { Content = "New toilet habit added", ContentType = "text/plain", StatusCode = 200 };
        }
    }
}
