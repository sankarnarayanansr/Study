import perl
 
# Simple arithmetics
six = perl.eval("3+3")

 
# Eval can also return functions
sum = perl.eval("sub { my $s = shift; $s += shift while @_; $s }")
print(sum)