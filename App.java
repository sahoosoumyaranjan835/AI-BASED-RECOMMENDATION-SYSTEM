package recommender;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.File;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Load dataset
            DataModel model = new FileDataModel(new File("src/main/resources/dataset.csv"));

            // Similarity measure
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Find neighborhood of users
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Build recommender
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Recommend for user 1, 3 recommendations
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Item ID: " + recommendation.getItemID() +
                        " , Preference Value: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
