function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%

h=(X*theta);
error= h-y;
internal=dot(error,error);
internal_2=dot(theta,theta);
J=1/m*(1/2*sum(internal)+lambda/2*(sum(internal_2)-theta(1,1)^2));

grad=1/m*(X'*(h-y));
grad(2:end, 1)=grad(2:end,1)+lambda/m*theta(2:end,1);
grad=grad(:);






% =========================================================================

grad = grad(:);

end
