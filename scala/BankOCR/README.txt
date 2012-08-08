BankOCR kata

Description from:
http://codingdojo.org/cgi-bin/wiki.pl?KataBankOCR

--------------------
v1:
I deviated from the base description of the kata some, mostly because I wanted to play with/learn about
scala and to try doing it in a different way than I've typically seen it done in the past.

I also entirely neglected the file reading/writing, as that was relatively uninteresting to me.  Instead, I decided
to add a generator that would generate the sequences of characters for me that I would have otherwise read from file,
including the ability to have it randomly permute a character in the digit to one of the other valid characters.

It turned out, however, that the random permutations wasn't at all useful for testing, as it sometimes caused one digit
to permute into another, instead of simply introducing "garbage" into the representation.  That ended up causing
some tests to "flip-flop".  I managed to refactor some of the tests to get around that issue by looking for the total
number of differences from what I expected, but this didn't work for all the tests.  At some point, I may refactor
those tests to use a statically described permuted character representation instead of a randomly generated one.
