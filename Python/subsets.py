
def recurseSubSets(i, arr , collected , total):
   collected_tuple = tuple(collected)
   if collected_tuple in total:
      return 
   if i < len(arr):
      recurseSubSets(i+1, arr , collected , total)
      total.add(collected_tuple)
      recurseSubSets(i+1, arr , collected + [arr[i]], total)
      
   return total


def generateSubsets():
   arr = [1,2,3,4,5]
   return recurseSubSets(0 , arr , [] , set())


print(generateSubsets())
