# noLobbyChat

A simple Spigot/Paper plugin that prevents players from chatting in lobby servers while allowing staff members to communicate freely.

## Features

- Blocks chat messages from regular players
- Allows staff members with permission to chat normally
- Configurable response message with color code support
- Optional silent mode to completely disable notification messages
- Simple commands to change the message and toggle silent mode

## Commands

- `/nlc` - Shows the current no-chat message and silent mode status
- `/nlc <message>` - Changes the no-chat message
  - Example: `/nlc &c&lSorry! &7Chat is disabled in the lobby`
- `/nlc silent <true/false>` - Toggles silent mode
  - Example: `/nlc silent true` (disables all notification messages)
  - Example: `/nlc silent false` (enables notification messages)

## Permissions

- `nolobbychat.admin` (default: op)
  - Allows using the /nlc command
  - Allows chatting in lobby
  
## Configuration

```yaml
# config.yml
message: "&c&lYou cannot chat in lobby, join a server to chat."
silent-mode: false
```

- `message`: The message sent to players when chat is disabled (supports color codes using `&`)
- `silent-mode`: When set to `true`, completely disables notification messages - chat will simply not work without any message being sent to the player

## Installation

1. Download the plugin JAR file
2. Place it in your server's `plugins` folder
3. Restart your server
4. Configure the message using /nlc command (optional)

## Requirements

- Spigot/Paper 1.20+
- Java 8 or higher
