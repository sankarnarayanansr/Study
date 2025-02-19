def sortByMethodology(lDiff):
   #RCE2023.03 creating a dictionary in same format as the hierarchy
   dColumnOrder={"Synth":{},"TetraMAX":{},"Primetime":{},"Formality":{}}
   #RCE2023.03 Rearranged List to be returned after sort
   lDiffSorted=[]
   #RCE2023.03 To store params of the col If present eg /All , /AllFail in the end
   dColParams={}
   for sCol in lDiff:
      #RCE2023.03 Storing the params of the columns in order to get it back while return eg /All , /AllFail , Since there are variable paramaters
      if "/" in sCol:
         lColSplit=sCol.split("/")
         dColParams[lColSplit[0]]=lColSplit[1]
   #RCE2023.03 set for storing all keys which is used later to parse all methodologies  eg TetraMAX.atpg_xxx/All atpg_xxx is the key , TetraMAX.syn_aaa.atpg_yyy/All syn_aaa is the key
   lKeys=set([])
   dSingleKeys={}
   for sCol in lDiff:
      iFlag=0
      for pVal in dColumnOrder:
         if sCol.startswith(pVal):
            iFlag=1
            lSubKeys=sCol.split(".",2)
            if len(lSubKeys)<=1:
                break
            elif len(lSubKeys)==2:
               #RCE2023.03 sMainKey will have methodology name , sSubKey will have internal string TetraMAX.atpg_xxx/All here TetraMAX in mainkey , aptg_xx is subkey
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               #RCE2023.03 Storing the string after the dot or between two dots into a {} , eg TetraMAX.atpg_xxx/All atpg_xxx is the key , TetraMAX.syn_aaa.atpg_yyy/All syn_aaa is the key
               lKeys.add(sSubKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey][sSubKey]=[]
            else:
               sMainKey=lSubKeys.pop(0).split("/")[0]
               sSubKey=lSubKeys.pop(0).split("/")[0]
               lKeys.add(sSubKey)
               if sSubKey in dColumnOrder[sMainKey]:
                  dColumnOrder[sMainKey][sSubKey].extend(lSubKeys)
               else:
                  dColumnOrder[sMainKey][sSubKey]=lSubKeys

      if iFlag==0:
         lDiffSorted.append(sCol)
   for sKey in lKeys:
      iTotalKeys=0
      for sMainKey in dColumnOrder:
         iKeyFlag=0
         for sSubKey in dColumnOrder[sMainKey]:
            if sSubKey==sKey:
               iKeyFlag+=1
         if iKeyFlag==1:
            iTotalKeys+=1
      if iTotalKeys==1:
         dSingleKeys[sKey]=1

   #RCE2023.03 Checking if there are any columns in the methodology , If present adding the methodology into the column list , If empty its omitted
   for sMainKey in dColumnOrder:
      if dColumnOrder[sMainKey]!={}:
         if sMainKey in dColParams:
            lDiffSorted.append("{}/{}".format(sMainKey,dColParams[sMainKey]))
         else:
            lDiffSorted.append("{}".format(sMainKey))
         for sSubKey in dColumnOrder[sMainKey]:
            if sSubKey in dSingleKeys:
               if "{}.{}".format(sMainKey,sSubKey) in dColParams:
                  lDiffSorted.append("{}/{}".format("{}.{}".format(sMainKey,sSubKey),dColParams[sMainKey]))
               else:
                  lDiffSorted.append("{}.{}".format(sMainKey,sSubKey))
               if len(dColumnOrder[sMainKey][sSubKey])!=0:
                  for sSingleCol in dColumnOrder[sMainKey][sSubKey]:
                     if "{}.{}.{}".format(sMainKey,sSubKey,sSingleCol) in dColParams:
                        lDiffSorted.append("{}/{}".format("{}.{}.{}".format(sMainKey,sSubKey,sSingleCol),dColParams[sMainKey]))
                     else:
                        lDiffSorted.append("{}.{}.{}".format(sMainKey,sSubKey,sSingleCol))
               lKeys.remove(sSubKey)
   #RCE2023.03 Arranging the columns based on the keys collected in lKeys , In such a way that more than one methodology has common key , Those columns will come together
   for sKey in lKeys:
      if sKey in dSingleKeys:
         continue
      for sMainKey in dColumnOrder:
         if sKey in dColumnOrder[sMainKey]:
            #RCE2023.03 reforming the column name with methodology , key and params collected
            sParamKey="{}.{}".format(sMainKey,sKey)
            if sParamKey in dColParams:
               lDiffSorted.append("{}.{}/{}".format(sMainKey,sKey,dColParams[sParamKey]))
            else:
               #RCE2023.03 Not adding params If there are no params present
               lDiffSorted.append("{}.{}".format(sMainKey,sKey))
            if len(dColumnOrder[sMainKey][sKey])>0:
               for sSubKey in dColumnOrder[sMainKey][sKey]:
                  lDiffSorted.append("{}.{}.{}".format(sMainKey,sKey,sSubKey))
   return lDiffSorted

print(sortByMethodology(['Synth','TetraMAX','Formality','Primetime','Primetime.z' ,'Synth.a','TetraMAX.a','Formality.a','Primetime.a','Synth.b','TetraMAX.b','TetraMAX.b.c','Formality.b','Primetime.b','Primetime.b.c']))