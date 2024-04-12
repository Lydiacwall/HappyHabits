using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IUserRepository
    {
        Task<IEnumerable<User>> GetAllUsers();
        Task<User> GetUserById(string userId);
        Task CreateUser(User user);
        Task UpdateUser(User user);
        Task DeleteUser(string userId);
    }
}
