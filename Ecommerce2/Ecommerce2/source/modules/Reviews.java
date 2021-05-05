package modules;

public class Reviews {
	
	public String reviewId;
	public String review;
	
	
	public Reviews(String review,Integer userId,String productId) {
		super();
		this.review = review;
		this.reviewId=productId+String.valueOf(userId);
		
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public String getReviewId() {
		return reviewId;
	}
	
	
}
