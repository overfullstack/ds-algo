Writing coding interview questions hasn't made me rich. Maybe trading Apple stocks will. Suppose we
could access yesterday's stock prices as an array, where:

The indices are the time in minutes past trade opening time, which was 9:30am local time. The values
are the price in dollars of Apple stock at that time. So if the stock cost $500 at 10:30am,
stockPricesYesterday[60] = 500.

Write an efficient function that takes stockPricesYesterday and returns the best profit I could have
made from 1 purchase and 1 sale of 1 Apple stock yesterday.

For example:

int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};

getMaxProfit(stockPricesYesterday); // returns 6 (buying for $5 and selling for $11)
