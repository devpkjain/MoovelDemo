# Mobile Developer Exercise

Below is a simple application that we need you to create using the tools of your choice. Please note the following rules;

1. Submissions must be a git repo, sent zipped via email (please don't post on github)
2. We expect to see commits at sensible points in your progress
3. Please *DO NOT* spend more than 2 hours on this exercise

On interview day you will be expected to solve and discuss similar tasks in Xcode or Android Studio in person in front of developers, if you find this task hard you will not pass our interview (and probably be very unhappy).

Now it's up to you! Solve this in any way you feel appropriate, no need for everything to be perfect, do your best to demonstrate your skill in the 2 hours (or less).


# Simple Mobile Ticketing Task

Welcome to the world of mobile ticketing! We're creating a brand new mobile application that allows users to select their fare before purchasing their bus tickets. This will consist of three screens:

1. Rider type selection
2. Fare type selection
3. Confirmation

All of the data required for these screens comes from a single JSON response with the following format:


```javascript
{
  "Adult": {
    "fares": [
      { "description": "2.5 Hour Ticket", "price": 2.5 },
      { "description": "1 Day Pass", "price": 5.0 },
      { "description": "30 Day Pass", "price": 100 }
    ],
    "subtext": null
  },
  "Child": {
    "fares": [
      { "description": "2.5 Hour Ticket", "price": 1.5 },
      { "description": "1 Day Pass", "price": 2.0 },
      { "description": "30 Day Pass", "price": 40.0 }
    ],
    "subtext": "Ages 8-17"
  },
  "Senior": {
    "fares": [
      { "description": "2.5 Hour Ticket", "price": 1.0 },
      { "description": "1 Day Pass", "price": 2.0 },
      { "description": "30 Day Pass", "price": 40.0 }
    ],
    "subtext": "Ages 60+"
  }
}
```

The design team has already created wireframes for what each of these screens should look like:
![http://i.imgur.com/NieOGom.png](http://i.imgur.com/NieOGom.png)

You've been tasked with creating these three screens and associated unit tests. Please make commits along the way using git and include any design notes in a `README.md` in the root directory. We encourage that you ask any questions via email. Please make a few notes on what you would do given more time, or if being tasked with something similar in your job.
