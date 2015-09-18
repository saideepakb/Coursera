function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

theta=[0.25 0.5 -0.5]' 
lambda=0.1
X = [ones(20,1) (exp(1) * sin(1:1:20))' (exp(0.5) * cos(1:1:20))'];
y = sin(X(:,1) + X(:,2)) > 0;


% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta


h=sigmoid(X*theta);

inside_cost_one=dot(-y,log(h)) - dot((1-y),log(1-h));
inside_cost_two=dot(theta,theta);
J=1/m * (sum(inside_cost_one)+(lambda/2)*(sum(inside_cost_two)-theta(1,1)^2));
	
J
inside_grad = X(:,1)'*(h-y);
grad(1,1) = 1/m*sum(inside_grad);
	
for j=2:columns(X)
	inside_grad = X(:,j)'*(h-y);
	grad(j,1) = 1/m*sum(inside_grad)+(lambda/m)*theta(j,1);
end




% =============================================================

end
