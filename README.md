# TradeReportEngine
Sample app which generates Trade Reports

Introduction:
Trade Reporting Engine needs to be devoloped which has input in below format:

Entity Buy/Sell AgreedFx Currency InstructionDate SettlementDate Units Price per unit
foo      B       0.50      SGP     01 Jan 2016     02 Jan 2016    200     100.25
bar      S       0.22      AED     05 Jan 2016     07 Jan 2016    450     150.5

1. A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where
the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
2. A trade can only be settled on a working day.
3. If an instructed settlement date falls on a weekend, then the settlement date should be changed to
the next working day.
4. USD amount of a trade = Price per unit * Units * Agreed Fx

Prerequisite:
1. Java8 is Required as Streams and Lambdas are used.
2. Complete input needs to be in input.txt file. This is to Externalize the Data without Tight coupling to code.

Assumptions:
1. The code will only ever be executed in a single threaded environment
2. We need this to be very lightweight that is why External Dependencies are minimized. That is the Reason this is not done using H2 Embedded DB or SpringBoot containers.
3. All data is in memory
4. Output format is in plain text, printed out to the console.

What it offers:
This generates the below types of Reports on Daily basis in given dates:

1. Amount in USD settled incoming everyday
2. Amount in USD settled outgoing everyday
3. Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
amount for a buy instruction, then foo is rank 1 for outgoing

How to Run:
1. Add the Data in input.txt file under src/main/resources in Space Separated for every Row.
2.Run main() method which prints all Reports.

Sample Output:
Reporting Engine starting...
Date                            |    Daily Incoming Highest Amount
Thu Jan 07 00:00:00 IST 2016    |    14899.500

Date                            |    Daily Outgoing Highest Amount

Sat Jan 02 00:00:00 IST 2016    |    10025.0000


Incoming:Settlement Date        |     Rank                   |    Entity
Thu Jan 07 00:00:00 IST 2016    |      1                     |    bar

Outgoing:Settlement Date        |     Rank                   |    Entity

Sat Jan 02 00:00:00 IST 2016    |      1                     |    foo

