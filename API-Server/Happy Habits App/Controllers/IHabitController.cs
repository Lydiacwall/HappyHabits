using Happy_Habits_App.Model;
using Microsoft.AspNetCore.Mvc;

namespace Happy_Habits_App.Controllers
{
    public interface IHabitController
    {
        Task<IActionResult> GetAllHabits();
        Task<IActionResult> GetHabitById(string id);
        Task<IActionResult> CreateHabit(Habit habit);
        Task<IActionResult> UpdateHabit(string id, Habit habit);
        Task<IActionResult> DeleteHabit(string id);
    }
}
