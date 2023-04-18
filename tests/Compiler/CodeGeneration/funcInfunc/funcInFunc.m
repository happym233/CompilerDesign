add0
          sw -80(r14), r15
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar8 := x
          lw r1,-4(r5)
          sw -48(r14), r1
          % processing: tmpVar7 := tmpVar8
          lw r1,-48(r14)
          sw -44(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-40(r14)
          sw -36(r14), r1
          addi r5, r14, 0
          %processing parameter0: x
          lw r1, -36(r14)
          sw -88(r14), r1
          % processing jump
          sw -116(r14), r14
          addi r14, r14, -84
          jl r15, multi21
          lw r1, -88(r14)
          sw -28(r14), r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar11 := y
          lw r1,-8(r5)
          sw -60(r14), r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar14 := z
          lw r1,-12(r5)
          sw -72(r14), r1
          % processing: tmpVar13 := tmpVar14
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar1 := tmpVar1 + tmpVar10
          lw r1,-20(r14)
          lw r2,-56(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar1 := tmpVar1 + tmpVar13
          lw r1,-20(r14)
          lw r2,-68(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing line50
          lw r1, -16(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -80(r14)
          lw r14, -76(r14)
          jr r15
          lw r15, -80(r14)
          lw r14, -76(r14)
          jr r15
multi21
          sw -36(r14), r15
          % processing line55
          % processing line55
          % processing line55
          % processing line55
          % processing: tmpVar18 := 2
          addi r1,r0,2
          sw -20(r14),r1
          % processing line55
          addi r5, r14, 0
          % processing: tmpVar20 := x
          lw r1,-4(r5)
          sw -28(r14), r1
          % processing: tmpVar17 := tmpVar18
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar17 := tmpVar17 * tmpVar20
          lw r1,-16(r14)
          lw r2,-28(r14)
          mul r1,r1,r2
          sw -16(r14),r1
          % processing: tmpVar16 := tmpVar17
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar15 := tmpVar16
          lw r1,-12(r14)
          sw -8(r14), r1
          % processing line55
          lw r1, -8(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -36(r14)
          lw r14, -32(r14)
          jr r15
          lw r15, -36(r14)
          lw r14, -32(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing line64
          % processing line64
          % processing line64
          % processing line64
          % processing: tmpVar24 := 1
          addi r1,r0,1
          sw -28(r14),r1
          % processing: tmpVar23 := tmpVar24
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing line64
          % processing line64
          % processing: tmpVar27 := 2
          addi r1,r0,2
          sw -40(r14),r1
          % processing line64
          % processing: tmpVar29 := 3
          addi r1,r0,3
          sw -48(r14),r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar26 := tmpVar26 * tmpVar29
          lw r1,-36(r14)
          lw r2,-48(r14)
          mul r1,r1,r2
          sw -36(r14),r1
          % processing: tmpVar22 := tmpVar23
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar22 := tmpVar22 + tmpVar26
          lw r1,-20(r14)
          lw r2,-36(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar21 := tmpVar22
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          sw -8(r5), r6
          % processing line65
          % processing read statement
          addi r5, r14, 0
          addi r1, r0, buf
          addi r14, r14, -144
          sw -8(r14), r1
          jl r15,getstr
          jl r15,strint
          addi r6, r13, 0
          subi r14, r14, -144
          sw -4(r5), r6
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          addi r5, r14, 0
          % processing: tmpVar38 := x
          lw r1,-4(r5)
          sw -84(r14), r1
          % processing: tmpVar37 := tmpVar38
          lw r1,-84(r14)
          sw -80(r14), r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar35 := tmpVar36
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          addi r5, r14, 0
          % processing: tmpVar42 := y
          lw r1,-8(r5)
          sw -100(r14), r1
          % processing: tmpVar41 := tmpVar42
          lw r1,-100(r14)
          sw -96(r14), r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-96(r14)
          sw -92(r14), r1
          % processing: tmpVar39 := tmpVar40
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing line66
          % processing line66
          % processing line66
          % processing line66
          addi r5, r14, 0
          % processing: tmpVar46 := z
          lw r1,-12(r5)
          sw -116(r14), r1
          % processing: tmpVar45 := tmpVar46
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing: tmpVar44 := tmpVar45
          lw r1,-112(r14)
          sw -108(r14), r1
          % processing: tmpVar43 := tmpVar44
          lw r1,-108(r14)
          sw -104(r14), r1
          addi r5, r14, 0
          %processing parameter0: x
          lw r1, -72(r14)
          sw -148(r14), r1
          %processing parameter1: y
          lw r1, -88(r14)
          sw -152(r14), r1
          %processing parameter2: z
          lw r1, -104(r14)
          sw -156(r14), r1
          % processing jump
          sw -220(r14), r14
          addi r14, r14, -144
          jl r15, add0
          lw r1, -148(r14)
          sw -64(r14), r1
          % processing: tmpVar32 := tmpVar33
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar31 := tmpVar32
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing: tmpVar30 := tmpVar31
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-52(r5)
          sw -12(r5), r6
          % processing line67
          % processing write statement
          % processing line67
          % processing line67
          % processing line67
          % processing line67
          addi r5, r14, 0
          % processing: tmpVar50 := z
          lw r1,-12(r5)
          sw -132(r14), r1
          % processing: tmpVar49 := tmpVar50
          lw r1,-132(r14)
          sw -128(r14), r1
          % processing: tmpVar48 := tmpVar49
          lw r1,-128(r14)
          sw -124(r14), r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-124(r14)
          sw -120(r14), r1
          lw r1, -120(r14)
          addi r14, r14, -144
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -144
          addi r1, r0, charLineSep
          addi r14, r14, -144
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -144
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
