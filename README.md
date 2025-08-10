# AI-BASED-RECOMMENDATION-SYSTEM

COMPANY : CODTECH IT SOLUTIONS
NAME : SOUMYA RANJAN SAHOO
INTERN ID : CT06DH674
DOMAIN : JAVA
DURATION : 6 WEEKS
MENTOR : NEELA SANTHOSH

This Java program implements a user-based collaborative filtering recommendation system using the Apache Mahout machine learning library. Its purpose is to suggest items to users based on the preferences of other users with similar tastes.

The code is contained in the recommender package and uses Mahout’s classes to load data, measure similarity, identify neighborhoods, and generate recommendations.

Key Components and Workflow
Importing Required Classes
The program imports several classes from the org.apache.mahout.cf.taste package:

FileDataModel – Loads a dataset from a CSV file.

UserSimilarity and PearsonCorrelationSimilarity – Used to calculate how similar two users are.

UserNeighborhood and NearestNUserNeighborhood – Identify the most similar users (neighbors).

GenericUserBasedRecommender – The actual recommender engine for user-based collaborative filtering.

RecommendedItem – Represents each recommended item with its ID and preference score.

Loading the Dataset

java
Copy
Edit
DataModel model = new FileDataModel(new File("src/main/resources/dataset.csv"));
The dataset.csv file contains user–item preference data in the form:

Copy
Edit
userID,itemID,preferenceValue
For example:

Copy
Edit
1,101,4.5
1,102,3.0
2,101,4.0
FileDataModel reads this file and stores it in a format Mahout can work with.

Calculating User Similarity

java
Copy
Edit
UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
The Pearson Correlation Coefficient measures how closely two users' preferences align.

A value of 1.0 means perfect similarity, 0 means no correlation, and -1.0 means opposite preferences.

Finding the User Neighborhood

java
Copy
Edit
UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
This finds the N most similar users for a given target user.

Here, 2 means each user’s recommendations will be based on the two most similar other users.

Building the Recommender

java
Copy
Edit
Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
This creates a User-Based Collaborative Filtering model.

The recommender will:

Find the neighbors of the target user.

Look at items those neighbors liked but the target user hasn’t rated yet.

Recommend the highest scoring ones.

Generating Recommendations

java
Copy
Edit
List<RecommendedItem> recommendations = recommender.recommend(1, 3);
Recommends items for user ID 1.

The second parameter (3) specifies the maximum number of items to recommend.

Displaying Recommendations

java
Copy
Edit
for (RecommendedItem recommendation : recommendations) {
    System.out.println("Recommended Item ID: " + recommendation.getItemID() +
                       " , Preference Value: " + recommendation.getValue());
}
Prints each recommended item’s ID and its predicted preference score.

How It Works in Practice
When the program runs:

It reads all user–item ratings from the CSV file.

For the target user (User 1), it finds the two most similar users using Pearson correlation.

It examines items liked by those similar users that User 1 has not yet rated.

It predicts User 1’s likely preference for those items.

It sorts them and recommends the top three.

Applications
This type of recommender can be used in:

E-commerce – Suggesting products.

Streaming platforms – Recommending movies or music.

E-learning – Suggesting courses based on similar learners.

