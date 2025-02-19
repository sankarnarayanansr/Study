def sortBy(lDiff):
   #RCE2023.03 creating a dictionary in same format as the hierarchy
   dColumnOrder={"Synth":{},"TetraMAX":{},"Primetime":{},"Formality":{}}
   lDiffSorted=[]
   dColParams={}
   for sCol in lDiff:
      #RCE2023.03 Storing the params of the columns in order to get it back while return eg /All , /AllFail , Since there are variable paramaters
      if "/" in sCol:
         lColSplit=sCol.split("/")
         dColParams[lColSplit[0]]=lColSplit[1]
   lKeys=set([])
   for sCol in lDiff:
      iFlag=0
      for pVal in dColumnOrder:
         if sCol.startswith(pVal):
            lSubKeys=sCol.split(".",2)
            if len(lSubKeys)<=1:
                break
            elif len(lSubKeys)==2:
               #RCE2023.03 sMainKey will have methodology name , sSubKey will have internal string TetraMAX.atpg_xxx/All here Tetramax in mainkey , aptg_xx is subkey
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               #RCE2023.03 Storing the string after the dot or between two dots into a set([]) , eg TetraMAX.atpg_xxx/All atpg_xxx is they key , TetraMAX.syn_aaa.atpg_yyy/All syn_aaa is the key
               lKeys.add(sSubKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey]={sSubKey:[]}
            else:
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               lKeys.add(sSubKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey][sSubKey]=lSubKeys
      if iFlag==1:
         lDiffSorted.append(sCol)
   #RCE2023.03 Checking if there are any columns in the methodology , If present adding the methodology into the column list , If empty its omitted
   for sMainKey in dColumnOrder:
      if dColumnOrder[sMainKey]!={}:
         lDiffSorted.append("{}/{}".format(sMainKey,dColParams[sMainKey]))
   #RCE2023.03 Arranging the columns based on the keys collected in lKeys , In such a way that more than one methodology has common key , Those columns will come together
   for sKey in lKeys:
      for sMainKey in dColumnOrder:
         if sKey in dColumnOrder[sMainKey]:
            #RCE2023.03 reforming the column name with methodology , key and params collected
            lDiffSorted.append("{}.{}/{}".format(sMainKey,sKey,dColParams["{}.{}".format(sMainKey,sKey)]))
            if len(dColumnOrder[sMainKey][sKey])>0:
               for sSubKey in dColumnOrder[sMainKey][sKey]:
                  lDiffSorted.append("{}.{}.{}".format(sMainKey,sKey,sSubKey))
   return lDiffSorted


def sortByPriority(lDiff):
   dColumnOrder={"Synth":{},"TetraMAX":{},"Primetime":{},"Formality":{}}
   lDiffSorted=[]
   dColParams={}
   for sCol in lDiff:
      if "/" in sCol:
         lColSplit=sCol.split("/")
         dColParams[lColSplit[0]]=lColSplit[1]
   lKeys=set([])
   for sCol in lDiff:
      print(sCol)

      iFlag=0
      for pVal in dColumnOrder:
         if sCol.startswith(pVal):
            lSubKeys=sCol.split(".",2)
            # print(lSubKeys)
            if len(lSubKeys)<=1:
                break
            elif len(lSubKeys)==2:
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               lKeys.add(sSubKey)
               # print(sMainKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey][sSubKey]=[]
            else:
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               lKeys.add(sSubKey)
               # print(sMainKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey][sSubKey]=lSubKeys
      print(dColumnOrder)
      if iFlag==1:
         lDiffSorted.append(sCol)
   print(dColumnOrder)
   for sMainKey in dColumnOrder:
      if dColumnOrder[sMainKey]!={}:
         lDiffSorted.append("{}/{}".format(sMainKey,dColParams[sMainKey]))
   for sKey in lKeys:
      for sMainKey in dColumnOrder:
         if sKey in dColumnOrder[sMainKey]:
            print(dColumnOrder[sMainKey][sKey])
            lDiffSorted.append("{}.{}/{}".format(sMainKey,sKey,dColParams["{}.{}".format(sMainKey,sKey)]))
            if len(dColumnOrder[sMainKey][sKey])>0:
               for sSubKey in dColumnOrder[sMainKey][sKey]:
                  lDiffSorted.append("{}.{}.{}".format(sMainKey,sKey,sSubKey))
   return lDiffSorted



print(sortByPriority(['Synth/All', 'TetraMAX/All', 'Synth.syn_aaa/All', 'TetraMAX.syn_aaa/All', 'TetraMAX.syn_aaa.atpg_yyy/All', 'TetraMAX.atpg_xxx/All']))