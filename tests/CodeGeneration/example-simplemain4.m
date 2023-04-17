printArr0
          sw -124(r14), r15
          % processing line3
          % processing line3
          % processing line3
          % processing line3
          % processing: tmpVar3 := 0
          addi r1,r0,0
          sw -28(r14),r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          sw -12(r5), r6
          % processing line4
gowhile1  
          % processing line4
          % processing line4
          % processing line4
          % processing line4
          addi r6, r14, 0
          % processing: tmpVar7 := z
          lw r2,-12(r6)
          sw -44(r14), r2
          % processing: tmpVar6 := tmpVar7
          lw r2,-44(r14)
          sw -40(r14), r2
          % processing: tmpVar5 := tmpVar6
          lw r2,-40(r14)
          sw -36(r14), r2
          % processing line4
          % processing line4
          % processing line4
          addi r6, r14, 0
          % processing: tmpVar11 := size
          lw r2,-8(r6)
          sw -60(r14), r2
          % processing: tmpVar10 := tmpVar11
          lw r2,-60(r14)
          sw -56(r14), r2
          % processing: tmpVar9 := tmpVar10
          lw r2,-56(r14)
          sw -52(r14), r2
          % processing: tmpVar4 := tmpVar5
          lw r2,-36(r14)
          sw -32(r14), r2
          % processing: tmpVar4 := tmpVar4 leq tmpVar9
          lw r2,-32(r14)
          lw r3,-52(r14)
          cle r2,r2,r3
          sw -32(r14),r2
          lw r1, -32(r14)
          bz r1, endwhile1
          % processing line5
          % processing write statement
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          addi r6, r14, 0
          % processing: tmpVar18 := z
          lw r2,-12(r6)
          sw -88(r14), r2
          % processing: tmpVar17 := tmpVar18
          lw r2,-88(r14)
          sw -84(r14), r2
          % processing: tmpVar16 := tmpVar17
          lw r2,-84(r14)
          sw -80(r14), r2
          addi r6, r14, 0
          % processing: tmpVar15 := arr
          addi r2, r0, 0 
          addi r3, r0, 4 
          % processing: dimension 0
          lw r4, -80(r6)
          add r2, r2, r4
          mul r2, r2, r3 
          muli r3, r3, 7 
          muli r2, r2, -1
          lw r5, -4(r6)
          add r2, r5, r2
          lw r4, 0(r2)
          sw -76(r14), r4
          % processing: tmpVar14 := tmpVar15
          lw r2,-76(r14)
          sw -72(r14), r2
          % processing: tmpVar13 := tmpVar14
          lw r2,-72(r14)
          sw -68(r14), r2
          % processing: tmpVar12 := tmpVar13
          lw r2,-68(r14)
          sw -64(r14), r2
          lw r2, -64(r14)
          addi r14, r14, -128
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -128
          addi r2, r0, charLineSep
          addi r14, r14, -128
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -128
          % processing line6
          % processing line6
          % processing line6
          % processing line6
          addi r6, r14, 0
          % processing: tmpVar22 := z
          lw r2,-12(r6)
          sw -104(r14), r2
          % processing: tmpVar21 := tmpVar22
          lw r2,-104(r14)
          sw -100(r14), r2
          % processing line6
          % processing line6
          % processing: tmpVar25 := 1
          addi r2,r0,1
          sw -116(r14),r2
          % processing: tmpVar24 := tmpVar25
          lw r2,-116(r14)
          sw -112(r14), r2
          % processing: tmpVar20 := tmpVar21
          lw r2,-100(r14)
          sw -96(r14), r2
          % processing: tmpVar20 := tmpVar20 + tmpVar24
          lw r2,-96(r14)
          lw r3,-112(r14)
          add r2,r2,r3
          sw -96(r14),r2
          % processing: tmpVar19 := tmpVar20
          lw r2,-96(r14)
          sw -92(r14), r2
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-92(r6)
          sw -12(r6), r7
          j gowhile1
endwhile1 
          lw r15, -124(r14)
          lw r14, -120(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -248
          add r1, r1, r14
          sw -4(r14), r1
          % processing line13
          % processing line13
          % processing line13
          % processing: tmpVar28 := 0
          addi r1,r0,0
          sw -16(r14),r1
          % processing: tmpVar27 := tmpVar28
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-12(r14)
          sw -8(r14), r1
          % processing line13
          % processing line13
          % processing line13
          % processing line13
          % processing: tmpVar32 := 64
          addi r1,r0,64
          sw -32(r14),r1
          % processing: tmpVar31 := tmpVar32
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing: tmpVar30 := tmpVar31
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar29 := tmpVar30
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-20(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -8(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line14
          % processing line14
          % processing line14
          % processing: tmpVar35 := 1
          addi r1,r0,1
          sw -44(r14),r1
          % processing: tmpVar34 := tmpVar35
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar33 := tmpVar34
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing line14
          % processing line14
          % processing line14
          % processing line14
          % processing: tmpVar39 := 34
          addi r1,r0,34
          sw -60(r14),r1
          % processing: tmpVar38 := tmpVar39
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing: tmpVar37 := tmpVar38
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-52(r14)
          sw -48(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-48(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -36(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line15
          % processing line15
          % processing line15
          % processing: tmpVar42 := 2
          addi r1,r0,2
          sw -72(r14),r1
          % processing: tmpVar41 := tmpVar42
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-68(r14)
          sw -64(r14), r1
          % processing line15
          % processing line15
          % processing line15
          % processing line15
          % processing: tmpVar46 := 25
          addi r1,r0,25
          sw -88(r14),r1
          % processing: tmpVar45 := tmpVar46
          lw r1,-88(r14)
          sw -84(r14), r1
          % processing: tmpVar44 := tmpVar45
          lw r1,-84(r14)
          sw -80(r14), r1
          % processing: tmpVar43 := tmpVar44
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-76(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -64(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line16
          % processing line16
          % processing line16
          % processing: tmpVar49 := 3
          addi r1,r0,3
          sw -100(r14),r1
          % processing: tmpVar48 := tmpVar49
          lw r1,-100(r14)
          sw -96(r14), r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-96(r14)
          sw -92(r14), r1
          % processing line16
          % processing line16
          % processing line16
          % processing line16
          % processing: tmpVar53 := 12
          addi r1,r0,12
          sw -116(r14),r1
          % processing: tmpVar52 := tmpVar53
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing: tmpVar51 := tmpVar52
          lw r1,-112(r14)
          sw -108(r14), r1
          % processing: tmpVar50 := tmpVar51
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-104(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -92(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line17
          % processing line17
          % processing line17
          % processing: tmpVar56 := 4
          addi r1,r0,4
          sw -128(r14),r1
          % processing: tmpVar55 := tmpVar56
          lw r1,-128(r14)
          sw -124(r14), r1
          % processing: tmpVar54 := tmpVar55
          lw r1,-124(r14)
          sw -120(r14), r1
          % processing line17
          % processing line17
          % processing line17
          % processing line17
          % processing: tmpVar60 := 22
          addi r1,r0,22
          sw -144(r14),r1
          % processing: tmpVar59 := tmpVar60
          lw r1,-144(r14)
          sw -140(r14), r1
          % processing: tmpVar58 := tmpVar59
          lw r1,-140(r14)
          sw -136(r14), r1
          % processing: tmpVar57 := tmpVar58
          lw r1,-136(r14)
          sw -132(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-132(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -120(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line18
          % processing line18
          % processing line18
          % processing: tmpVar63 := 5
          addi r1,r0,5
          sw -156(r14),r1
          % processing: tmpVar62 := tmpVar63
          lw r1,-156(r14)
          sw -152(r14), r1
          % processing: tmpVar61 := tmpVar62
          lw r1,-152(r14)
          sw -148(r14), r1
          % processing line18
          % processing line18
          % processing line18
          % processing line18
          % processing: tmpVar67 := 11
          addi r1,r0,11
          sw -172(r14),r1
          % processing: tmpVar66 := tmpVar67
          lw r1,-172(r14)
          sw -168(r14), r1
          % processing: tmpVar65 := tmpVar66
          lw r1,-168(r14)
          sw -164(r14), r1
          % processing: tmpVar64 := tmpVar65
          lw r1,-164(r14)
          sw -160(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-160(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -148(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line19
          % processing line19
          % processing line19
          % processing: tmpVar70 := 6
          addi r1,r0,6
          sw -184(r14),r1
          % processing: tmpVar69 := tmpVar70
          lw r1,-184(r14)
          sw -180(r14), r1
          % processing: tmpVar68 := tmpVar69
          lw r1,-180(r14)
          sw -176(r14), r1
          % processing line19
          % processing line19
          % processing line19
          % processing line19
          % processing: tmpVar74 := 90
          addi r1,r0,90
          sw -200(r14),r1
          % processing: tmpVar73 := tmpVar74
          lw r1,-200(r14)
          sw -196(r14), r1
          % processing: tmpVar72 := tmpVar73
          lw r1,-196(r14)
          sw -192(r14), r1
          % processing: tmpVar71 := tmpVar72
          lw r1,-192(r14)
          sw -188(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-188(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -176(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line20
          % processing line20
          % processing line20
          % processing line20
          % processing line20
          addi r5, r14, 0
          % processing: tmpVar79 := arr
          lw r1,-4(r5)
          sw -220(r14), r1
          % processing: tmpVar78 := tmpVar79
          lw r1,-220(r14)
          sw -216(r14), r1
          % processing: tmpVar77 := tmpVar78
          lw r1,-216(r14)
          sw -212(r14), r1
          % processing: tmpVar76 := tmpVar77
          lw r1,-212(r14)
          sw -208(r14), r1
          % processing line20
          % processing line20
          % processing line20
          % processing line20
          % processing: tmpVar83 := 6
          addi r1,r0,6
          sw -236(r14),r1
          % processing: tmpVar82 := tmpVar83
          lw r1,-236(r14)
          sw -232(r14), r1
          % processing: tmpVar81 := tmpVar82
          lw r1,-232(r14)
          sw -228(r14), r1
          % processing: tmpVar80 := tmpVar81
          lw r1,-228(r14)
          sw -224(r14), r1
          addi r5, r14, 0
          %processing parameter0: arr
          lw r1, -208(r14)
          sw -280(r14), r1
          %processing parameter1: size
          lw r1, -224(r14)
          sw -284(r14), r1
          % processing jump
          sw -396(r14), r14
          addi r14, r14, -276
          jl r15, printArr0
          lw r1, -4(r14)
          lw r2, -8(r14)
hlt

floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          lw r3, -12(r14)
          lw r4, -16(r14)
gowhileFAdd
          cne r5, r2, r4
          bz r5, endwhileFAdd
          clt r6, r2, r4
          bz r6, elseFAdd
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFAdd
elseFAdd
          muli r1, r1, 10
          subi r2, r2, 1
endIfFAdd
          j gowhileFAdd
endwhileFAdd
          add  r1, r1, r3
          sw   -4(r14), r1
          sw   -8(r14), r2
          jr   r15
floatMul
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
          mul r1, r1, r3
          add r2, r2, r4
          sw  -4(r14), r1
          sw  -8(r14), r2
          jr   r15
floatLeq
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
gowhileFLeq
          cne r5, r2, r4
          bz   r5, endwhileFLeq
          clt r6, r2, r4
          bz r6, elseFLeq
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFLeq
elseFLeq
          muli r1, r1, 10
          subi r2, r2, 1
          endIfFLeq
          j gowhileFLeq
endwhileFLeq 
          cle r1, r1, r3
          sw -4(r14), r1
          jr r15

buf       res 20
charE     db "e", 0
charLineSep    db "  ", 0
