def findInitial(rules):
    first='S';
    contador=rules.count(first)

    if(len(rules)==0):
        return first
    else:
            for i in range(len(rules)) : 
                for j in range(len(rules[i])) : 
                    if(rules[i][j]=="S"):
                        return first
            else:
                return rules[0][0]


rules = [
  ['A', 'a', 'B', 'b'],
  ['B', 'A'],
  ['B'],
  ['S', 'A']
]

print(findInitial(rules))