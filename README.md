# Avancerad-java-Erik-Svensson-slutprojekt
Slutprojekt på Avancerad Javaprogrammering 2024

Här är ett förbättrat och mer detaljerat README för ditt projekt, med tillägg av formatering, emojis och en mer strukturerad layout:

```markdown
# Todo-List 3000 🚀
**Todo-List 3000** är en JavaFX-applikation som låter användare hantera en lista med uppgifter. Applikationen integrerar med ett REST API för att skapa, uppdatera och ta bort uppgifter, vilket gör det enkelt att hålla koll på din "att göra"-lista.

## 🛠 Funktionalitet
- **Visa alla uppgifter** 📝: Applikationen hämtar och visar alla uppgifter från backend-API:t.
- **Lägg till en uppgift** ➕: Skapa en ny uppgift genom att ange en beskrivning.
- **Markera som klar** ✅: Markera en uppgift som "klar" eller "inte klar".
- **Ta bort en uppgift** ❌: Ta bort en specifik uppgift från listan.

## 🧰 Teknologier
- **JavaFX**: Används för att bygga det grafiska användargränssnittet.
- **Spring Boot (Backend API)**: Tillhandahåller ett REST API för att hantera uppgifter.
- **REST API**: För att hämta, skapa, uppdatera och ta bort uppgifter.

## 📋 Förutsättningar
För att köra applikationen, säkerställ att följande är installerat:

- **Java 8+** (Notera: JavaFX ingår i JDK för Java 8, men med Java 11+ behöver du lägga till JavaFX som ett externt bibliotek).
- **Spring Boot Backend**: Du behöver en Spring Boot-backend för att tillhandahålla API:et.

## ⚙️ Installation

### Backend (Spring Boot)
1. Klona eller skapa ett Spring Boot-projekt som hanterar en lista med uppgifter (Todo).
2. Lägg till en REST-controller med följande endpoints:
   - `GET /todos` - Hämta alla uppgifter.
   - `POST /todos` - Skapa en ny uppgift.
   - `PUT /todos/{id}` - Uppdatera en uppgift (markera som klar eller inte).
   - `DELETE /todos/{id}` - Ta bort en uppgift.

3. Kör backend-tjänsten på en port, exempelvis `http://localhost:8080`.

---

### Frontend (JavaFX)
1. Klona detta repository:
   ```bash
   git clone https://github.com/username/todo-list-3000.git
   ```
2. Lägg till nödvändiga beroenden för JavaFX i ditt projekt.
3. Justera `BASE_URL` i frontend-koden för att matcha din backend:
   ```java
   private static final String BASE_URL = "http://localhost:8080/todos";
   ```
4. Kör applikationen via din IDE eller kommandoraden.

---

## 🖥️ Användning
1. Starta backend-API:t (Spring Boot).
2. Kör frontend-applikationen (JavaFX).
3. Använd applikationen för att:
   - **Lägga till uppgifter**: Skriv en beskrivning i textfältet och klicka på "Lägg till".
   - **Markera som klar**: Välj en uppgift och klicka på "Klar".
   - **Ta bort uppgifter**: Välj en uppgift och klicka på "Ta bort".

---

## 📁 Projektstruktur
- **`TodoFrontend.java`**: JavaFX-applikationen som hanterar GUI och API-anrop.
- **`Todo.java`**: Modellklass som representerar en Todo-uppgift.

---

## 🚀 Förbättringsmöjligheter
- Möjlighet att redigera uppgifter 🖊️.
- Fler sätt att organisera uppgifter, t.ex. kategorier 📂.
- Bättre felhantering och användarupplevelse 🎨.

---

## 📜 Licens
Det här projektet är licensierat under Erik Svensson Inc. Ltd.

---

🎉 Tack för att du använder Todo-List 3000! Lycka till med dina uppgifter! 🚀
```
