using Happy_Habits_App.Forms;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/Workout/[controller]")]
    [ApiController]
    public class ExercisesWorkoutController : ControllerBase
    {
        private readonly IExercisesWorkoutActivitiesService _exercisesWorkoutActivitiesService;

        public ExercisesWorkoutController(IExercisesWorkoutActivitiesService exercisesWorkoutActivitiesService)
        {
            _exercisesWorkoutActivitiesService = exercisesWorkoutActivitiesService;
        }

        [HttpPost("AddHabit")]
        public async Task<IActionResult> CreateHabit([FromBody] ExercisesWorkoutForm form)
        {
            Console.WriteLine("Trying to add exercisesWorkout activity");

            if (!form.IsValid)
            {
                Console.WriteLine("400");
                return BadRequest("Not valid attributes");
            }

            Console.WriteLine("200");
            await _exercisesWorkoutActivitiesService.AddExerciseWorkoutActivity(form);
            return Ok("Weights added succesfully");
        }
    }
}
