using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IMessageService
    {
        public Task<List<MessageDto>> GetMessagesOfGroupChat(string groupId);

        public Task StoreMessage(string message, string senderId, string groupId, DateTime today);

        public Task<List<FriendGroupDto>> GetAllFriendGroups(string userId);
    }
}
