using Happy_Habits_App.Model;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FastActivityController : ControllerBase, IHabitController
    {
        private readonly IFastActivityService _fastActivitiesService;
        public Task<IActionResult> CreateHabit(Habit habit)
        {
            throw new NotImplementedException();
        }

        public Task<IActionResult> DeleteHabit(string id)
        {
            throw new NotImplementedException();
        }

        public Task<IActionResult> GetAllHabits()
        {
            throw new NotImplementedException();
        }

        public Task<IActionResult> GetHabitById(string id)
        {
            throw new NotImplementedException();
        }

        public Task<IActionResult> UpdateHabit(string id, Habit habit)
        {
            throw new NotImplementedException();
        }
    }
}
