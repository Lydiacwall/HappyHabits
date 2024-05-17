using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.SignalR;

namespace Happy_Habits_App.Hubs
{
    public class ChatHub : Hub
    {
        private readonly IMessageService _messageService;
        public ChatHub(IMessageService messageService) => _messageService = messageService;
        public async Task SendMessage(string groupId, string senderId, string message)
        {
            DateTime today = DateTime.Now;
            // Store message
            Console.WriteLine("Store message: " + message + "from user: " + senderId);
            await _messageService.StoreMessage(message, senderId, groupId, today);

            // Send message to both users

            Console.WriteLine("Receive message: " + message);

            await Clients.Group(groupId).SendAsync("ReceiveMessage", senderId, message, today.ToString());
        }

        public async Task AddToGroup(string groupId)
        {
            await Groups.AddToGroupAsync(Context.ConnectionId, groupId);
        }

/*        public async Task RemoveFromGroup(string groupId)
        {
            await Groups.RemoveFromGroupAsync(Context.ConnectionId, groupId);
        }*/
    }
}
