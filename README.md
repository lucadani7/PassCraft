# PassCraft

**PassCraft** is a JavaFX Password Generator with support for multiple password generation, strength indication, and easy copy to clipboard.

## Disclaimer

> ⚠ **PassCraft is an educational project and is not intended to replace established password managers or online password generators.**
> Use generated passwords at your own risk. For highly secure passwords, consider using trusted services like LastPass, 1Password, or Bitwarden.

## 🚀 Features

- Generate multiple passwords at once
- Password strength indicator (progress bar + label)
- Copy selected password to clipboard
- Configurable length, lowercase, uppercase, digits, and symbols
- Easy-to-use JavaFX GUI

## Requirements

- Java 17+
- Maven
- JavaFX 21+

## Running the project

1. Clone the repository:
   ```bash
   git clone https://github.com/lucadani7/PassCraft.git
   cd PassCraft
   ```
2. Run the project using Maven:
   ```bash
   mvn javafx:run
   ```
3. Alternatively, build the JAR:
   ```bash
   mvn clean package
   java -jar target/PassCraft-1.0-SNAPSHOT.jar
   ```
   > *Make sure JavaFX libraries are available on your system (Maven will handle dependencies).*

## 📄 License
This project is licensed under the Apache-2.0 License.
